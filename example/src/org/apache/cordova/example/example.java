/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.apache.cordova.example;

import android.os.Bundle;
import android.webkit.WebSettings;

import org.apache.cordova.*;

public class example extends DroidGap
{
	
   // private static final AccessDB AccessDB = null;
    private static final AccessDB access=new AccessDB();

	@Override
    public void onCreate(Bundle savedInstanceState)
    {   	
        super.onCreate(savedInstanceState);
        super.init();      
        appView.getSettings().setJavaScriptEnabled(true);
		appView.addJavascriptInterface(access,"moveToJava");
		//appView.setInitialScale(100);
		//appView.loadData("text/HTML", "UTF-8");
	
		
        super.loadUrl(Config.getStartUrl());
        
       //super.loadUrl("http://env-8055445.jelastic.servint.net");
       // super.loadUrl("assets/www/index.html");
    }
}

