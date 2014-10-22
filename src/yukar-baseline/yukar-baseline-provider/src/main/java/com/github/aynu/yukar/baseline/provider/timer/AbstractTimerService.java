// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.timer;
import java.util.Date;
/**
 * 基底タイマーサービス
 * @author nilcy
 */
public abstract class AbstractTimerService {
    /** 最終実行日時 */
    private Date lastTimeout;
    /** コンストラクタ */
    public AbstractTimerService() {
    }
    /**
     * {@link #lastTimeout} の取得
     * @return {@link #lastTimeout}
     */
    public String getLastTimeout() {
        return lastTimeout != null ? lastTimeout.toString() : "N/A";
    }
    /**
     * {@link #lastTimeout} の設定
     * @param lastTimeout {@link #lastTimeout}
     */
    public void setLastTimeout(final Date lastTimeout) {
        this.lastTimeout = lastTimeout;
    }
}
