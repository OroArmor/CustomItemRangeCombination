/*
 * MIT License
 *
 * Copyright (c) 2021 OroArmor (Eli Orona)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.oroarmor.custom_item_range_combination;

import java.io.File;

import com.google.common.collect.ImmutableList;
import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;
import com.oroarmor.config.command.ConfigCommand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

public class Mod implements ModInitializer {

	public static final String MOD_ID = "custom_item_range_combination";

	public static class ModConfig extends Config {
		public ModConfig() {
			super(ImmutableList.of(new ConfigGroup()), new File(FabricLoader.getInstance().getConfigDir().toFile(), MOD_ID+".json"), "item_combine");
		}

		public static class ConfigGroup extends ConfigItemGroup {
			public ConfigGroup() {
				super(ImmutableList.of(ITEM_COMBINE_RANGE), "range");
			}

			public static final ConfigItem<Double> ITEM_COMBINE_RANGE = new ConfigItem<>("item_combine_range", 0.5, MOD_ID+".item_combine_range", configItem -> Mod.CONFIG.saveConfigToFile());
		}
	}

	public static final Config CONFIG = new ModConfig();

	@Override
	public void onInitialize() {
		CONFIG.readConfigFromFile();
		CONFIG.saveConfigToFile();

		CommandRegistrationCallback.EVENT.register(new ConfigCommand(CONFIG)::register);
	}
}
