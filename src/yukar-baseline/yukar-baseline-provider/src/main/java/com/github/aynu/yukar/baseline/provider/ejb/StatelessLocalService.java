// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.ejb;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
/**
 * ステートレスセッションBeanサービス(ローカルBean)
 * @author nilcy
 */
@Stateless
@LocalBean
@SuppressWarnings("static-method")
public class StatelessLocalService {
    /** コンストラクタ */
    public StatelessLocalService() {
    }
    /**
     * 挨拶メソッド
     * @param name 名前
     * @return 挨拶文(例:こんにちは %s さん。)
     */
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
