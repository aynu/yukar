// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.framework.domain;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * 設定ID
 * @author nilcy
 */
@Embeddable
public class ConfigId {
    /** 設定名 */
    @Column(name = "config_name", nullable = false)
    private String name;
    /** 設定キー */
    @Column(name = "config_key", nullable = false)
    private String key;
    /** コンストラクタ */
    public ConfigId() {
    }
    /**
     * {@link #name} の取得
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }
    /**
     * {@link #name} の設定
     * @param name {@link #name}
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * {@link #key} の取得
     * @return {@link #key}
     */
    public String getKey() {
        return key;
    }
    /**
     * {@link #key} の設定
     * @param key {@link #key}
     */
    public void setKey(final String key) {
        this.key = key;
    }
}
