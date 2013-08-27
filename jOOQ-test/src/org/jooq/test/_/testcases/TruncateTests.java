/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 * 
 * This work is dual-licensed Open Source, under LGPL and jOOQ EULA
 * =============================================================================
 * You may freely choose which license applies to you. For more information 
 * about licensing, please visit http://www.jooq.org/licenses
 * 
 * LGPL:  
 * -----------------------------------------------------------------------------
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library.
 * If not, see http://www.gnu.org/licenses.
 * 
 * jOOQ End User License Agreement:
 * -----------------------------------------------------------------------------
 * This library is commercial software; you may not redistribute it nor
 * modify it.
 * 
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ End User
 * License Agreement for more details.
 * 
 * You should have received a copy of the jOOQ End User License Agreement
 * along with this library.
 * If not, see http://www.jooq.org/eula
 */
package org.jooq.test._.testcases;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.jooq.SQLDialect.CUBRID;
import static org.jooq.SQLDialect.FIREBIRD;
import static org.jooq.SQLDialect.INGRES;
import static org.jooq.SQLDialect.SQLITE;

import java.sql.Date;
import java.util.Arrays;

import org.jooq.InsertResultStep;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Record6;
import org.jooq.TableRecord;
import org.jooq.UpdatableRecord;
import org.jooq.test.BaseTest;
import org.jooq.test.jOOQAbstractTest;

import org.junit.Test;

public class TruncateTests<
    A    extends UpdatableRecord<A> & Record6<Integer, String, String, Date, Integer, ?>,
    AP,
    B    extends UpdatableRecord<B>,
    S    extends UpdatableRecord<S> & Record1<String>,
    B2S  extends UpdatableRecord<B2S> & Record3<String, Integer, Integer>,
    BS   extends UpdatableRecord<BS>,
    L    extends TableRecord<L> & Record2<String, String>,
    X    extends TableRecord<X>,
    DATE extends UpdatableRecord<DATE>,
    BOOL extends UpdatableRecord<BOOL>,
    D    extends UpdatableRecord<D>,
    T    extends UpdatableRecord<T>,
    U    extends TableRecord<U>,
    UU   extends UpdatableRecord<UU>,
    I    extends TableRecord<I>,
    IPK  extends UpdatableRecord<IPK>,
    T725 extends UpdatableRecord<T725>,
    T639 extends UpdatableRecord<T639>,
    T785 extends TableRecord<T785>>
extends BaseTest<A, AP, B, S, B2S, BS, L, X, DATE, BOOL, D, T, U, UU, I, IPK, T725, T639, T785> {

    public TruncateTests(jOOQAbstractTest<A, AP, B, S, B2S, BS, L, X, DATE, BOOL, D, T, U, UU, I, IPK, T725, T639, T785> delegate) {
        super(delegate);
    }

    @Test
    public void testTruncate() throws Exception {
        jOOQAbstractTest.reset = false;

        try {
            create().truncate(TAuthor()).execute();

            // The above should fail if foreign keys are supported
            if (!Arrays.asList(CUBRID, FIREBIRD, INGRES, SQLITE).contains(dialect())) {
                fail();
            }
        } catch (Exception expected) {
        }

        // This is being tested with an unreferenced table as some RDBMS don't
        // Allow this
        create().truncate(TDates()).execute();
        assertEquals(0, create().fetch(TDates()).size());
    }

    @Test
    public void testTruncateCascade() throws Exception {
        switch (dialect().family()) {
            case ASE:
            case CUBRID:
            case DB2:
            case DERBY:
            case FIREBIRD:
            case H2:
            case HSQLDB:
            case INGRES:
            case MARIADB:
            case MYSQL:
            case ORACLE:
            case SQLITE:
            case SQLSERVER:
            case SYBASE:
                log.info("SKIPPING", "TRUNCATE CASCADE tests");
                return;
        }

        jOOQAbstractTest.reset = false;

        try {
            create().truncate(TAuthor())
                    .restrict()
                    .execute();
        } catch (Exception expected) {
        }

        // This is being tested with an unreferenced table as some RDBMS don't
        // Allow this
        create().truncate(TAuthor())
                .cascade()
                .execute();
        assertEquals(0, create().fetch(TAuthor()).size());
        assertEquals(0, create().fetch(TBook()).size());
    }

    @Test
    public void testTruncateRestartIdentity() throws Exception {
        switch (dialect().family()) {
            case ASE:
            case CUBRID:
            case DB2:
            case DERBY:
            case FIREBIRD:
            case H2:
            case INGRES:
            case MARIADB:
            case MYSQL:
            case ORACLE:
            case SQLITE:
            case SQLSERVER:
            case SYBASE:
                log.info("SKIPPING", "RESTART IDENTITY tests");
                return;
        }

        jOOQAbstractTest.reset = false;

        InsertResultStep<I> insert =
        create().insertInto(TIdentity(), TIdentity_VAL())
                .values(1)
                .returning(TIdentity_ID());
        int id1 = insert.fetchOne().getValue(TIdentity_ID());


        create().truncate(TIdentity())
                .continueIdentity()
                .execute();
        int id2 = insert.fetchOne().getValue(TIdentity_ID());
        assertEquals(id1 + 1, id2);


        create().truncate(TIdentity())
                .restartIdentity()
                .execute();
        int id3 = insert.fetchOne().getValue(TIdentity_ID());
        assertEquals(1, id3);
    }
}
