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
 * The final step in creating a case statement of the type <code><pre>
 * CASE x WHEN 1 THEN 'one'
 *        WHEN 2 THEN 'two'
 *        ELSE        'three'
 * END
 * </pre></code>
 *
 * @param <V> The type of values being compared in this case statement
 * @param <T> The type returned by this case statement
 * @author Lukas Eder
 * @see Case
 */
public interface CaseWhenStep<V, T> extends Field<T> {

    /**
     * Compare a value to the already constructed case statement, return result
     * if values are equal.
     *
     * @param compareValue The value to compare with the already constructed
     *            case statement
     * @param result The result value if values are equal
     * @return An intermediary step for case statement construction
     */
    @Support
    CaseWhenStep<V, T> when(V compareValue, T result);

    /**
     * Compare a value to the already constructed case statement, return result
     * if values are equal.
     *
     * @param compareValue The value to compare with the already constructed
     *            case statement
     * @param result The result value if values are equal
     * @return An intermediary step for case statement construction
     */
    @Support
    CaseWhenStep<V, T> when(V compareValue, Field<T> result);

    /**
     * Compare a value to the already constructed case statement, return result
     * if values are equal.
     *
     * @param compareValue The value to compare with the already constructed
     *            case statement
     * @param result The result value if values are equal
     * @return An intermediary step for case statement construction
     */
    @Support
    CaseWhenStep<V, T> when(Field<V> compareValue, T result);

    /**
     * Compare a value to the already constructed case statement, return result
     * if values are equal.
     *
     * @param compareValue The value to compare with the already constructed
     *            case statement
     * @param result The result value if values are equal
     * @return An intermediary step for case statement construction
     */
    @Support
    CaseWhenStep<V, T> when(Field<V> compareValue, Field<T> result);

    /**
     * Add an else clause to the already constructed case statement
     *
     * @param result The result value if no other value matches the case
     * @return The resulting field from case statement construction
     */
    @Support
    Field<T> otherwise(T result);

    /**
     * Add an else clause to the already constructed case statement
     *
     * @param result The result value if no other value matches the case
     * @return The resulting field from case statement construction
     */
    @Support
    Field<T> otherwise(Field<T> result);
}
