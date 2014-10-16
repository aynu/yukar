// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.testing;
import java.util.Iterator;
import java.util.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
/**
 * アーカイブヘルパー
 * <dl>
 * <dt>使用条件
 * <dd>インテグレーションテスト(Arquillian)のアーカイブ操作に使用すること。
 * </dl>
 * @author nilcy
 */
public final class ArchiveHelper {
    /** ロガー */
    private static final Logger LOG = Logger.getGlobal();
    /** 非公開コンストラクタ */
    private ArchiveHelper() {
    }
    /**
     * アーカイブのトレース
     * <dl>
     * <dt>使用条件
     * <dd>アーカイブ中のパスをログ出力するために使用すること。
     * </dl>
     * @param archive アーカイブ
     */
    public static void trace(final Archive<?> archive) {
        final Iterator<ArchivePath> iter = archive.getContent().keySet().iterator();
        final StringBuilder builder = new StringBuilder();
        while (iter.hasNext()) {
            final ArchivePath path = iter.next();
            builder.append(String.format("%s\n", path.get()));
        }
        LOG.info(builder.toString());
    }
}
