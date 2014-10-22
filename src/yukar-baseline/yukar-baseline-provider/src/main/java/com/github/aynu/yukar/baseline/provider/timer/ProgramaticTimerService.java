// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.timer;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import org.slf4j.Logger;
/**
 * 手動タイマーサービス
 * @author nilcy
 */
@Singleton
public class ProgramaticTimerService extends AbstractTimerService {
    /** タイマーサービス */
    @Resource
    private TimerService timerService;
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public ProgramaticTimerService() {
    }
    /**
     * タイマー作成
     * @param duration タイムアウト時間(ミリ秒)
     */
    public void setTimer(final long duration) {
        log.info("タイムアウト時間(ミリ秒) : {}", Long.valueOf(duration));
        timerService.createTimer(duration, "手動タイマー#01");
    }
    /**
     * タイムアウト処理
     * @param timer タイマー
     */
    @Timeout
    public void timeout(final Timer timer) {
        setLastTimeout(new Date());
        log.info("手動タイマーサービスが終了しました。[{}]", timer.getInfo());
    }
}
