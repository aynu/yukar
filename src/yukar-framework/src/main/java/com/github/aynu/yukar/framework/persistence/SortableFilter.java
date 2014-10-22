// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework.persistence;
/**
 * ソート条件
 * @author nilcy
 */
public interface SortableFilter {
    /**
     * ソート条件 の取得
     * @return ソート条件
     */
    String[] getOrders();
}
