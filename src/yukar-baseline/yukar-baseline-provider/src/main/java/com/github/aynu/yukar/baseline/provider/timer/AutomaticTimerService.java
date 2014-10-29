// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.timer;
import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.slf4j.Logger;
/**
 * 自動タイマーサービス
 * @author nilcy
 */
@Singleton
public class AutomaticTimerService extends AbstractTimerService {
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public AutomaticTimerService() {
    }
    /**
     * タイムアウト処理
     */
    @Schedule(hour = "*", minute = "*/10")
    public void timeout() {
        setLastTimeout(new Date());
        log.info("自動タイマーサービスが終了しました。");
    }
}
