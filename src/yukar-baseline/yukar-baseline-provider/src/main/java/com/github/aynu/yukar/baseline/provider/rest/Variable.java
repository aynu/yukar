// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.rest;
import javax.xml.bind.annotation.XmlRootElement;
import com.github.aynu.yukar.framework.lang.AbstractDataObject;
/**
 * 変数
 * @author nilcy
 */
@XmlRootElement
public class Variable extends AbstractDataObject<Variable> {
    /** 識別番号 */
    private static final long serialVersionUID = -8070242521884495985L;
    /** foo */
    private String foo;
    /** bar */
    private String bar;
    /** baz */
    private String baz;
    /** コンストラクタ */
    public Variable() {
    }
    /**
     * コンストラクタ
     * @param foo {@link #foo}
     * @param bar {@link #bar}
     * @param baz {@link #baz}
     */
    public Variable(final String foo, final String bar, final String baz) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
    }
    /**
     * {@link #foo} の取得
     * @return {@link #foo}
     */
    public String getFoo() {
        return foo;
    }
    /**
     * {@link #foo} の設定
     * @param foo {@link #foo}
     */
    public void setFoo(final String foo) {
        this.foo = foo;
    }
    /**
     * {@link #bar} の取得
     * @return {@link #bar}
     */
    public String getBar() {
        return bar;
    }
    /**
     * {@link #bar} の設定
     * @param bar {@link #bar}
     */
    public void setBar(final String bar) {
        this.bar = bar;
    }
    /**
     * {@link #baz} の取得
     * @return {@link #baz}
     */
    public String getBaz() {
        return baz;
    }
    /**
     * {@link #baz} の設定
     * @param baz {@link #baz}
     */
    public void setBaz(final String baz) {
        this.baz = baz;
    }
}
