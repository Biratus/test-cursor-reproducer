This is a reproducer following this: https://quarkusio.zulipchat.com/#narrow/stream/187030-users/topic/SQL.20Cursor.20-.20Mutiny.20-.20Native.20image

The problem is that in native image a NPE is thrown when selecting from the database.

The actual sources reproduce the issue.
To make it work:

Comment Client.java l.87
Comment Repository.java l.3

It seems the problem is related to the number of fields retreived from the database...

The error only appears in native image  
