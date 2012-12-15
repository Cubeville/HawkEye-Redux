package org.cubeville.hawkeye.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.representer.Representer;

/**
 * Configuration node implementation using a yaml file for storage
 */
public class YamlConfiguration extends ConfigurationNode implements FileConfiguration {

	/**
	 * File to store config in
	 */
	private final File file;

	/**
	 * Internal yaml object
	 */
	private final Yaml yaml;

	public YamlConfiguration(File file, boolean writeDefaults) {
		super(new HashMap<String, Object>(), writeDefaults);

		if (file == null) throw new IllegalArgumentException("File cannot be null");
		this.file = file;

		DumperOptions options = new DumperOptions();
		options.setIndent(4);
		options.setDefaultFlowStyle(FlowStyle.BLOCK);

		yaml = new Yaml(new SafeConstructor(), new Representer(), options);
	}

	@Override
	public void load() {
		load(file);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(File file) {
		Object obj = null;
		Map<String, Object> data = new HashMap<String, Object>();

		try {
			obj = yaml.load(new FileInputStream(file));
		} catch (IOException ignore) {
		}

		if (obj instanceof Map) {
			data = (Map<String, Object>) obj;
		}

		root = data;
	}

	@Override
	public void save() {
		save(file);
	}

	@Override
	public void save(File file) {
		OutputStream stream = null;

		try {
			if (file.getParentFile() != null) file.getParentFile().mkdirs();

			stream = new FileOutputStream(file);
			yaml.dump(root, new OutputStreamWriter(stream));
		} catch (IOException ignore) {
		} finally {
			try {
				if (stream != null) stream.close();
			} catch (IOException ignore) {
			}
		}
	}

	@Override
	public void reload() {
		clear();
		load(file);
	}

}
