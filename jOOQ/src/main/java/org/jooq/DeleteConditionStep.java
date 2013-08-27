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

import org.jooq.api.annotation.State;
import org.jooq.impl.DSL;


/**
 * This type is used for the {@link Delete}'s DSL API.
 * <p>
 * Example: <code><pre>
 * DSLContext create = DSL.using(configuration);
 *
 * create.delete(table)
 *       .where(field1.greaterThan(100))
 *       .execute();
 * </pre></code>
 *
 * @author Lukas Eder
 */
@State
public interface DeleteConditionStep<R extends Record> extends DeleteFinalStep<R> {

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#AND} operator
     */
    @Support
    DeleteConditionStep<R> and(Condition condition);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#AND} operator
     */
    @Support
    DeleteConditionStep<R> and(Field<Boolean> condition);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#AND} operator
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#condition(String)
     */
    @Support
    DeleteConditionStep<R> and(String sql);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#AND} operator
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#condition(String, Object...)
     */
    @Support
    DeleteConditionStep<R> and(String sql, Object... bindings);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#AND} operator
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#condition(String, QueryPart...)
     */
    @Support
    DeleteConditionStep<R> and(String sql, QueryPart... parts);

    /**
     * Combine the currently assembled conditions with a negated other one using
     * the {@link Operator#AND} operator
     */
    @Support
    DeleteConditionStep<R> andNot(Condition condition);

    /**
     * Combine the currently assembled conditions with a negated other one using
     * the {@link Operator#AND} operator
     */
    @Support
    DeleteConditionStep<R> andNot(Field<Boolean> condition);

    /**
     * Combine the currently assembled conditions with an EXISTS clause using
     * the {@link Operator#AND} operator
     */
    @Support
    DeleteConditionStep<R> andExists(Select<?> select);

    /**
     * Combine the currently assembled conditions with a NOT EXISTS clause using
     * the {@link Operator#AND} operator
     */
    DeleteConditionStep<R> andNotExists(Select<?> select);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#OR} operator
     */
    @Support
    DeleteConditionStep<R> or(Condition condition);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#OR} operator
     */
    @Support
    DeleteConditionStep<R> or(Field<Boolean> condition);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#OR} operator
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#condition(String)
     */
    @Support
    DeleteConditionStep<R> or(String sql);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#OR} operator
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#condition(String, Object...)
     */
    @Support
    DeleteConditionStep<R> or(String sql, Object... bindings);

    /**
     * Combine the currently assembled conditions with another one using the
     * {@link Operator#OR} operator
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#condition(String, QueryPart...)
     */
    @Support
    DeleteConditionStep<R> or(String sql, QueryPart... parts);

    /**
     * Combine the currently assembled conditions with a negated other one using
     * the {@link Operator#OR} operator
     */
    @Support
    DeleteConditionStep<R> orNot(Condition condition);

    /**
     * Combine the currently assembled conditions with a negated other one using
     * the {@link Operator#OR} operator
     */
    @Support
    DeleteConditionStep<R> orNot(Field<Boolean> condition);

    /**
     * Combine the currently assembled conditions with an EXISTS clause using
     * the {@link Operator#OR} operator
     */
    @Support
    DeleteConditionStep<R> orExists(Select<?> select);

    /**
     * Combine the currently assembled conditions with a NOT EXISTS clause using
     * the {@link Operator#OR} operator
     */
    @Support
    DeleteConditionStep<R> orNotExists(Select<?> select);
}
