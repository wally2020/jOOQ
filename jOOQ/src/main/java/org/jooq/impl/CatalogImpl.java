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

import static org.jooq.Clause.CATALOG;
import static org.jooq.Clause.CATALOG_REFERENCE;

import java.util.Collections;
import java.util.List;

import org.jooq.BindContext;
import org.jooq.Catalog;
import org.jooq.Clause;
import org.jooq.Context;
import org.jooq.RenderContext;
import org.jooq.Schema;
import org.jooq.tools.StringUtils;

/**
 * A common base class for database catalogs
 * <p>
 * This type is for JOOQ INTERNAL USE only. Do not reference directly
 *
 * @author Lukas Eder
 */
public class CatalogImpl extends AbstractQueryPart implements Catalog {

    /**
     * Generated UID
     */
    private static final long     serialVersionUID = -3650318934053960244L;
    private static final Clause[] CLAUSES          = { CATALOG, CATALOG_REFERENCE };
    private final String          catalogName;

    public CatalogImpl(String name) {
        super();

        this.catalogName = name;
    }

    @Override
    public final String getName() {
        return catalogName;
    }

    @Override
    public final void bind(BindContext context) {}

    @Override
    public final void toSQL(RenderContext context) {
        context.literal(getName());
    }

    @Override
    public final Clause[] clauses(Context<?> ctx) {
        return CLAUSES;
    }

    @Override
    public final Schema getSchema(String name) {
        for (Schema schema : getSchemas()) {
            if (schema.getName().equals(name)) {
                return schema;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Subclasses should override this method
     */
    @Override
    public List<Schema> getSchemas() {
        return Collections.emptyList();
    }

    // ------------------------------------------------------------------------
    // XXX: Object API
    // ------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        // [#1626] CatalogImpl equality can be decided without executing the
        // rather expensive implementation of AbstractQueryPart.equals()
        if (that instanceof CatalogImpl) {
            return StringUtils.equals(getName(), (((CatalogImpl) that).getName()));
        }

        return super.equals(that);
    }
}
