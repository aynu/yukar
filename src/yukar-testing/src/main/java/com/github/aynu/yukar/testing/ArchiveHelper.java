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
