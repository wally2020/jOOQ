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
package org.jooq;

/**
 * An object that can behave like a table (a table-like object)
 *
 * @param <R> The record type
 * @author Lukas Eder
 */
public interface TableLike<R extends Record> extends QueryPart {

    /**
     * Get this table's fields as a {@link Row}
     */
    Row fieldsRow();

    /**
     * Get a specific field from this Record.
     *
     * @see Row#field(Field)
     */
    <T> Field<T> field(Field<T> field);

    /**
     * Get a specific field from this Record.
     *
     * @see Row#field(String)
     */
    Field<?> field(String name);

    /**
     * Get a specific field from this Record.
     *
     * @see Row#field(int)
     */
    Field<?> field(int index);

    /**
     * Get all fields from this Record.
     *
     * @see Row#fields()
     */
    Field<?>[] fields();

    /**
     * The underlying table representation of this object
     * <p>
     * This method is useful for things like
     * <code>SELECT * FROM (SELECT * FROM x WHERE x.a = '1') WHERE ... </code>
     */
    @Support
    Table<R> asTable();

    /**
     * The underlying aliased table representation of this object
     *
     * @see Table#as(String)
     */
    @Support
    Table<R> asTable(String alias);

    /**
     * The underlying aliased table representation of this object
     *
     * @see Table#as(String, String...)
     */
    @Support
    Table<R> asTable(String alias, String... fieldAliases);
}
