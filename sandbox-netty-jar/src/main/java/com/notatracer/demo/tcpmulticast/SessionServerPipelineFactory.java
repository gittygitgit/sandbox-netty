/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.notatracer.demo.tcpmulticast;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.notatracer.demo.tcpmulticast.handler.RequestDecoder;
import com.notatracer.demo.tcpmulticast.handler.RequestValidator;
import com.notatracer.demo.tcpmulticast.handler.SubscriptionHandler;

/**
 * Creates a newly configured {@link ChannelPipeline} for a server-side channel.
 * 
 * @author <a href="http://www.jboss.org/netty/">The Netty Project</a>
 * @author <a href="http://gleamynode.net/">Trustin Lee</a>
 * 
 * @version $Rev: 2080 $, $Date: 2010-01-26 18:04:19 +0900 (Tue, 26 Jan 2010) $
 * 
 */
public class SessionServerPipelineFactory implements ChannelPipelineFactory {

	private String activeSession = null;

	public SessionServerPipelineFactory(String sessionToServe) {
		this.activeSession = sessionToServe;
	}

	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = pipeline();
        
        pipeline.addLast("decoder", new RequestDecoder());
        pipeline.addLast("validator", new RequestValidator(activeSession));
        pipeline.addLast("encoder", new StringEncoder());

        return pipeline;
    }
}
