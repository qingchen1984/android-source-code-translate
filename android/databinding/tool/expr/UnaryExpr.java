/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.databinding.tool.expr;

import android.databinding.tool.reflection.ModelAnalyzer;
import android.databinding.tool.reflection.ModelClass;

import java.util.List;

public class UnaryExpr extends Expr {
    final String mOp;
    UnaryExpr(String op, Expr expr) {
        super(expr);
        mOp = op;
    }

    @Override
    protected String computeUniqueKey() {
        return join(getOpStr(), getExpr().getUniqueKey());
    }

    @Override
    protected ModelClass resolveType(ModelAnalyzer modelAnalyzer) {
        return getExpr().getResolvedType();
    }

    @Override
    protected List<Dependency> constructDependencies() {
        return constructDynamicChildrenDependencies();
    }

    private String getOpStr() {
        switch (mOp.charAt(0)) {
            case '~' : return "bitNot";
            case '!' : return "logicalNot";
            case '-' : return "unaryMinus";
            case '+' : return "unaryPlus";
        }
        return mOp;
    }

    public String getOp() {
        return mOp;
    }

    public Expr getExpr() {
        return getChildren().get(0);
    }
}
