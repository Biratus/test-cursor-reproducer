package test.clement;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Cursor;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public abstract class BasicRepository<T extends SQLEntity> {

    protected abstract T fromRow(Row row);

    public Multi<RowSet<Row>> splitResult(final Cursor cursor, Integer batchSize) {
        return Multi.createBy().repeating()
                .uni(() -> {
                    log.info("READ "+batchSize);
                    return cursor.read(batchSize);
                })// read cursor
                .indefinitely()
                .onFailure(cause -> {
                    log.debug("Catch IllegalStateException on cursor. On force la fermeture du curseur");
                    return cause instanceof IllegalStateException;
                }).recoverWithCompletion() // If cursor is ended don't break async stream and finish
                .onFailure().invoke(failure -> log.error("ERROR", failure))
                .onCompletion().invoke(() -> cursor.close() // close when finished
                        .subscribe()
                        .with(s -> log.info("Cursor closed"), f -> log.error("Cursor close fail {}", f.getLocalizedMessage()))
                );
    }

    public List<T> transformRowSet(RowSet<Row> rows) {
        return StreamSupport
                .stream(rows.spliterator(), false)
                .map(this::fromRow)
                .collect(Collectors.toList());
    }
}