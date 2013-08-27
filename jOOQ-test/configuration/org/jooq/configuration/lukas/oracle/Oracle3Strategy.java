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
package org.jooq.configuration.lukas.oracle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

/**
 * A sample custom strategy
 *
 * @author Lukas Eder
 */
public class Oracle3Strategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaIdentifier(Definition definition) {
        return definition.getOutputName();
    }

    @Override
    public String getJavaSetterName(Definition definition, Mode mode) {
        return "set" + definition.getOutputName();
    }

    @Override
    public String getJavaGetterName(Definition definition, Mode mode) {
        return "get" + definition.getOutputName();
    }

    @Override
    public String getJavaMethodName(Definition definition, Mode mode) {
        return "call_" + definition.getOutputName();
    }

    @Override
    public String getJavaClassExtends(Definition definition, Mode mode) {
        // A little useless fun

        if (definition.getName().toLowerCase().contains("book")) {
            return Object.class.getName();
        }
        else {
            return ThreadDeath.class.getName();
        }
    }

    @Override
    public List<String> getJavaClassImplements(Definition definition, Mode mode) {
        if (mode == Mode.POJO) {
            if (definition.getName().toLowerCase().contains("book")) {
                return Arrays.asList(Serializable.class.getName());
            }
            else {
                return Arrays.asList(Cloneable.class.getName());
            }
        }
        else {
            if (definition.getName().toLowerCase().contains("library")) {
                return Arrays.asList(Serializable.class.getName(), Cloneable.class.getName());
            }
            else {
                return Arrays.asList(Cloneable.class.getName(), Cloneable.class.getName());
            }
        }
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        if (mode == Mode.POJO) {
            return definition.getOutputName() + "_POJO";
        }
        if (mode == Mode.INTERFACE) {
            return definition.getOutputName() + "_INTERFACE";
        }

        return definition.getOutputName();
    }

    @Override
    public String getJavaPackageName(Definition definition, Mode mode) {
        return super.getJavaPackageName(definition, mode);
    }

    @Override
    public String getJavaMemberName(Definition definition, Mode mode) {
        return definition.getOutputName();
    }

    @Override
    public String getOverloadSuffix(Definition definition, Mode mode, String overloadIndex) {
        return "______ABC_" + overloadIndex;
    }
}
