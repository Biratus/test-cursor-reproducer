package test.clement;

import io.vertx.mutiny.sqlclient.Tuple;

import java.util.List;

public interface SQLEntity {

    // Le nom de la table dans la base de donnée
    String getEntityName();

    // Le nom des champs pour l'insertion
    List<String> getDatabaseFields();

    // Le noms des champs pour le select, on ajoute updateDate car c'est une valeur mis à jour automatiquement qu'on ne veut pas insérer
    default List<String> getDatabaseFieldsForSelect() {
        List<String> l = getDatabaseFields();
        l.add("updateDate");
        return l;
    }

    // Transforme l'objet en Tuple, pour permettre les preparedQuery d'insertion en base
    Tuple toTuple();

}
