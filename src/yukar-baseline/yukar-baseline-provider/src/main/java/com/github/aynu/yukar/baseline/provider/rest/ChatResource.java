// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.rest;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
/**
 * チャットリソース
 * @author nilcy
 */
@Path("/chat/")
@Singleton
public class ChatResource {
    /** キュー */
    private final BlockingQueue<AsyncResponseWrapper> suspended = new ArrayBlockingQueue<>(10);
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public ChatResource() {
    }
    /**
     * 非同期メッセージ取得
     * @param asyncResponse 非同期レスポンス
     * @param requestId リクエストID
     */
    @SuppressWarnings("synthetic-access")
    @GET
    public void getMessage(@Suspended final AsyncResponse asyncResponse,
        @HeaderParam("request-id") final String requestId) {
        new Thread(() -> {
            try {
                suspended.put(new AsyncResponseWrapper(asyncResponse, requestId));
            } catch (final InterruptedException e) {
                log.warn(e.toString(), e);
            }
        }).start();
    }
    /**
     * 非同期メッセージ追加
     * @param asyncResponse 非同期レスポンス
     * @param message 送信メッセージ
     */
    @POST
    public void postMessage(@Suspended final AsyncResponse asyncResponse, final String message) {
        new Thread(() -> {
            try {
                final AsyncResponseWrapper responseWrapper = suspended.take();
                responseWrapper.getAsyncResponse().resume(
                    Response.ok().entity(message).header("request-id", responseWrapper.getId())
                        .build());
                asyncResponse.resume("Sent!");
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    /**
     * 非同期レスポンスキュー概要の取得
     * @return 非同期レスポンスキュー概要
     */
    @GET
    @Path("queue")
    @Produces("text/html")
    public String getResponseQueue() {
        final StringBuilder builder = new StringBuilder();
        for (final AsyncResponseWrapper asyncResponseWrapper : suspended) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(asyncResponseWrapper.getId());
        }
        return builder.toString();
    }
    /** 非同期レスポンスラッパー */
    private static final class AsyncResponseWrapper {
        /** 非同期レスポンス */
        private final AsyncResponse asyncResponse;
        /** ID */
        private final String id;
        /**
         * コンストラクタ
         * @param asyncResponse {@link #asyncResponse}
         * @param id {@link #id}
         */
        private AsyncResponseWrapper(final AsyncResponse asyncResponse, final String id) {
            this.asyncResponse = asyncResponse;
            this.id = id;
        }
        /**
         * {@link #asyncResponse} の取得
         * @return {@link #asyncResponse}
         */
        public AsyncResponse getAsyncResponse() {
            return asyncResponse;
        }
        /**
         * {@link #id} の取得
         * @return {@link #id}
         */
        public String getId() {
            return id;
        }
    }
}
