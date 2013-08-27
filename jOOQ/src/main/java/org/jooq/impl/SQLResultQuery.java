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
package org.jooq.impl;

import java.sql.ResultSetMetaData;

import org.jooq.BindContext;
import org.jooq.Clause;
import org.jooq.Configuration;
import org.jooq.Context;
import org.jooq.Field;
import org.jooq.QueryPart;
import org.jooq.QueryPartInternal;
import org.jooq.Record;
import org.jooq.RenderContext;

/**
 * A plain SQL query that returns results
 *
 * @author Lukas Eder
 */
class SQLResultQuery extends AbstractResultQuery<Record> {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = 1740879770879469220L;

    private final QueryPart   delegate;

    public SQLResultQuery(Configuration configuration, QueryPart delegate) {
        super(configuration);

        this.delegate = delegate;
    }

    // ------------------------------------------------------------------------
    // ResultQuery API
    // ------------------------------------------------------------------------

    @Override
    public final void toSQL(RenderContext context) {
        context.visit(delegate);
    }

    @Override
    public final void bind(BindContext context) {
        context.visit(delegate);
    }

    @Override
    public final Clause[] clauses(Context<?> ctx) {
        if (delegate instanceof QueryPartInternal) {
            return ((QueryPartInternal) delegate).clauses(ctx);
        }

        return null;
    }

    @Override
    public final Class<? extends Record> getRecordType() {
        return RecordImpl.class;
    }

    @Override
    protected final Field<?>[] getFields(ResultSetMetaData meta) {
        Configuration configuration = configuration();
        return new MetaDataFieldProvider(configuration, meta).getFields();
    }

    @Override
    final boolean isSelectingRefCursor() {
        return false;
    }

    @Override
    final boolean isForUpdate() {
        return false;
    }
}
