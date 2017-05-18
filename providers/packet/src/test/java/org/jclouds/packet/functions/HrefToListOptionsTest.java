/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.packet.functions;

import org.jclouds.packet.domain.Href;
import org.jclouds.packet.domain.options.ListOptions;
import org.testng.annotations.Test;

import com.google.common.collect.Multimap;

import static com.google.common.collect.Iterables.getOnlyElement;
import static org.jclouds.packet.domain.options.ListOptions.PAGE_PARAM;
import static org.jclouds.packet.domain.options.ListOptions.PER_PAGE_PARAM;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

@Test(groups = "unit", testName = "HrefToListOptionsTest")
public class HrefToListOptionsTest {

   public void testNoOptions() {
      HrefToListOptions function = new HrefToListOptions();

      ListOptions options = function.apply(Href.create("https://api.packet.net/projects"));
      assertNotNull(options);

      Multimap<String, String> params = options.buildQueryParameters();
      assertFalse(params.containsKey(PAGE_PARAM));
      assertFalse(params.containsKey(PER_PAGE_PARAM));
   }

   public void testWithOptions() {
      HrefToListOptions function = new HrefToListOptions();

      ListOptions options = function.apply(Href.create("https://api.packet.net/projects?page=2&per_page=5"));
      assertNotNull(options);

      Multimap<String, String> params = options.buildQueryParameters();
      assertEquals(getOnlyElement(params.get(PAGE_PARAM)), "2");
      assertEquals(getOnlyElement(params.get(PER_PAGE_PARAM)), "5");
   }

}
