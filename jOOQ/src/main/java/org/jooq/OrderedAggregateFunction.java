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

import static org.jooq.SQLDialect.ORACLE11G;
import static org.jooq.SQLDialect.ORACLE12C;

import java.util.Collection;

import org.jooq.api.annotation.State;
import org.jooq.api.annotation.Transition;

/**
 * An ordered aggregate function.
 * <p>
 * An ordered aggregate function is an aggregate function with a mandatory
 * Oracle-specific <code>WITHIN GROUP (ORDER BY ..)</code> clause. An example is
 * <code>LISTAGG</code>: <code><pre>
 * SELECT   LISTAGG(TITLE, ', ')
 *          WITHIN GROUP (ORDER BY TITLE)
 * FROM     T_BOOK
 * GROUP BY AUTHOR_ID
 * </pre></code> The above function groups books by author and aggregates titles
 * into a concatenated string.
 * <p>
 * Ordered aggregate functions can be further converted into window functions
 * using the <code>OVER(PARTITION BY ..)</code> clause. For example: <code><pre>
 * SELECT LISTAGG(TITLE, ', ')
 *        WITHIN GROUP (ORDER BY TITLE)
 *        OVER (PARTITION BY AUTHOR_ID)
 * FROM   T_BOOK
 * </pre></code>
 *
 * @author Lukas Eder
 */
@State
public interface OrderedAggregateFunction<T> {

    /**
     * Add an <code>WITHIN GROUP (ORDER BY ..)</code> clause to the ordered
     * aggregate function
     */
    @Support({ ORACLE11G, ORACLE12C })
    @Transition(
        name = "WITHIN GROUP ORDER BY",
        args = "Field+",
        to = "OrderedAggregateFunction"
    )
    AggregateFunction<T> withinGroupOrderBy(Field<?>... fields);

    /**
     * Add an <code>WITHIN GROUP (ORDER BY ..)</code> clause to the ordered
     * aggregate function
     */
    @Support({ ORACLE11G, ORACLE12C })
    @Transition(
        name = "WITHIN GROUP ORDER BY",
        args = "SortField+",
        to = "OrderedAggregateFunction"
    )
    AggregateFunction<T> withinGroupOrderBy(SortField<?>... fields);

    /**
     * Add an <code>WITHIN GROUP (ORDER BY ..)</code> clause to the ordered
     * aggregate function
     */
    @Support({ ORACLE11G, ORACLE12C })
    @Transition(
        name = "WITHIN GROUP ORDER BY",
        args = "SortField+",
        to = "OrderedAggregateFunction"
    )
    AggregateFunction<T> withinGroupOrderBy(Collection<SortField<?>> fields);
}
