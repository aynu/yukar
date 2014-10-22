// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.soap;
import javax.jws.WebService;
/**
 * 挨拶サービスI/F
 * @author nilcy
 */
@WebService(name = "HelloService", serviceName = "HelloService")
public interface HelloService {
    /**
     * 挨拶オペレーション
     * @param name 名前
     * @return こんにちは %s さん。
     */
    String sayHello(String name);
}
