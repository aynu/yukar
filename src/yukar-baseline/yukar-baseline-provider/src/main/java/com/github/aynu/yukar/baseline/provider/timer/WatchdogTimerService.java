// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.timer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.slf4j.Logger;
/**
 * 監視タイマーサービス
 * @author nilcy
 */
@Startup
@Singleton
public class WatchdogTimerService {
    /** 自動タイマーサービス */
    @EJB
    private AutomaticTimerService automaticService;
    /** 手動タイマーサービス */
    @EJB
    private ProgramaticTimerService programaticService;
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public WatchdogTimerService() {
    }
    /** 初期処理 */
    @PostConstruct
    public void init() {
        programaticService.setTimer(30000);
    }
    /** タイムアウト処理 */
    @Schedule(minute = "*/1", hour = "*")
    public void timeout() {
        log.info("監視)自動タイマーサービス最終実行日時 : {}", automaticService.getLastTimeout());
        log.info("監視)手動タイマーサービス最終実行日時 : {}", programaticService.getLastTimeout());
    }
}
