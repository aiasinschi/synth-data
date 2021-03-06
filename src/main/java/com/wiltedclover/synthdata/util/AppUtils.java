package com.wiltedclover.synthdata.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class AppUtils {

	private static List<String> wordList;

	static {
		wordList = new ArrayList<>();
		String fileName = "static/english.txt";
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		URL resource = classLoader.getResource(fileName);
		File file = null;
		if (resource != null) {
			file = new File(resource.getFile());
		}
		if ((file != null) && (file.exists())) {
			try {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					wordList.add(scanner.nextLine());
				}
			} catch (FileNotFoundException ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	private static final String[] LOREM_IPSUM = {
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque justo libero, sollicitudin a auctor vitae, dignissim at massa. Donec non turpis neque. In quis pharetra odio. Etiam nulla sem, finibus scelerisque cursus convallis, sodales vitae est. Morbi fringilla nisl lorem, sed placerat dolor sagittis malesuada. Nulla aliquam purus quis urna blandit vulputate quis sed elit. Curabitur sodales ante convallis, feugiat enim eget, varius mauris. Suspendisse quis ornare velit. Etiam condimentum sem quis pellentesque viverra. Cras eu venenatis risus, id sodales elit. Vivamus ut magna leo. Vestibulum mattis urna vitae ex ultricies lobortis. Vivamus sit amet sem et mauris rutrum sollicitudin et tempus eros. Pellentesque vitae porta sapien, non tristique turpis. Phasellus non sollicitudin erat.",
			"Sed ornare vestibulum mattis. Suspendisse vulputate sodales elit, vel vestibulum tellus. Ut enim odio, vulputate gravida ex tincidunt, placerat sollicitudin urna. Donec sed eros justo. Integer sed sollicitudin elit. Maecenas pretium quis orci ut ultrices. Nulla facilisi. Nunc malesuada mattis lacus, ut congue diam aliquet ut. Quisque imperdiet venenatis luctus. Suspendisse suscipit vulputate odio, a egestas massa fermentum in.",
			"Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras ac nisi vel nibh volutpat vulputate in quis magna. Integer aliquet tristique lacus a feugiat. In suscipit turpis libero, et dignissim tellus mollis non. Praesent sit amet feugiat leo, sit amet rutrum dui. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec at nibh vitae erat mattis varius. Ut vulputate finibus justo, congue pretium enim aliquam vitae. Aliquam velit dolor, viverra eu ligula pulvinar, volutpat sollicitudin nisl. Donec sed scelerisque dolor. Donec pellentesque orci non metus finibus, faucibus efficitur sem faucibus. Ut sagittis, lectus eget sagittis commodo, magna velit viverra lorem, vitae viverra urna felis nec nibh. Pellentesque convallis malesuada neque eget pretium.",
			"Mauris mattis est mauris, in malesuada lacus vehicula et. Nam consectetur, nulla tempor accumsan cursus, nisi nibh egestas dolor, vel condimentum purus urna eget nulla. Ut malesuada velit vitae mauris hendrerit aliquet. Curabitur feugiat euismod mauris, quis facilisis neque vulputate id. Morbi elit velit, accumsan nec mauris eu, egestas egestas neque. Morbi id consectetur turpis, ac egestas nulla. Curabitur elementum in dui in egestas. Proin vehicula felis tempus turpis hendrerit malesuada. Mauris laoreet, est quis lobortis vulputate, est orci rutrum orci, at convallis mi quam in nunc. Donec lacus tortor, hendrerit nec sem sed, aliquam semper ligula. Duis vitae bibendum est. Fusce sit amet euismod lacus. Aliquam pretium rutrum enim, in facilisis orci laoreet eget.",
			"Donec quam dolor, dapibus nec ultricies eu, suscipit et leo. Nullam hendrerit neque nec augue porta rhoncus. Aenean euismod mi vel dolor hendrerit, et condimentum odio malesuada. Sed non luctus arcu. Cras sodales dui sit amet mi hendrerit imperdiet. Nulla eget risus vitae tortor viverra convallis sed sit amet ex. Morbi tincidunt, turpis quis fringilla suscipit, erat ante fringilla metus, vel laoreet metus enim sed justo. Fusce sed mollis tortor. Aliquam id fermentum nulla, sit amet cursus leo. Aliquam ac congue nulla."
	};

	public static String getRandomLoremIpsum() {
		return LOREM_IPSUM[(int) Math.round(Math.random()  * 100000000) % LOREM_IPSUM.length];
	}

	public static String getRandomTitle() {
		int count = random(4) + 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(getRandomWord());
			sb.append(" ");
		}
		return toSentenceCase(sb.toString());
	}

	public static String getRandomWord() {
		if (wordList.size() == 0) { // should not happen
			return "Dummy_title";
		}
		int index = random(wordList.size());
		return wordList.get(index);
	}

	private static String toSentenceCase(String toString) {
		return String.format("%s%s", toString.substring(0, 1).toUpperCase(), toString.substring(1));
	}

	public static int random(int max) {
		return (int)Math.round((Math.random() * 200000000) % max);
	}
}
