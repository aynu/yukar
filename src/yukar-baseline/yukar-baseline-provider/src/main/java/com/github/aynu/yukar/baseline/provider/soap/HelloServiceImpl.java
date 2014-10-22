// ----------------------------------------------------------------------------
// Copyright (C) Yukar Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.github.aynu.yukar.baseline.provider.soap;
import javax.jws.WebService;
/**
 * 挨拶サービス
 * @author nilcy
 */
@WebService(name = "HelloService", serviceName = "HelloService")
public class HelloServiceImpl implements HelloService {
    /** コンストラクタ */
    public HelloServiceImpl() {
    }
    /** {@inheritDoc} */
    @Override
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
