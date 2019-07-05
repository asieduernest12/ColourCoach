package interfaze;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ColorUtil {

	String dirPath = "";
	final String colorFileName = "colors.css";
	File colorFile = null;
	InputStream colorsIn = null;

	public ArrayList<Color> colors;

	public ColorUtil() {
		// colorFile = new File(System.getProperty("user.dir") + "\\"
		// + colorFileName);
		// if (!colorFile.exists()) {
		//
		// JOptionPane
		// .showMessageDialog(
		// null,
		// "File does not exist",
		// "File \"colors.css\" does not exist\n Application terminating!",
		// JOptionPane.ERROR_MESSAGE);
		//
		// System.exit(0);
		// }

		// load color.css from within project package as internal resource
		try {
			// colorsIn = getClass().getResourceAsStream("colors.css");
			colorsIn = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("resource/colors.css");
		} catch (Exception e) {
			// TODO: handle exception
		}
		colors = new ArrayList<>();

		loadColorArray();
	}

	public void loadColorArray() {
		Scanner in = null;

		try {

			in = new Scanner(colorsIn);
			int i = 0;

			while (in.hasNextLine()) {
				String cName = in.findInLine("\\w+");
				String cValue = in.findInLine("[#][\\w]+");
				Color tempColor = new Color(cName, cValue);
				colors.add(tempColor);
				System.out.print(String.format("%-5d%-25s%-8s\n", i++, cName,
						cValue));
				in.skip("; }");
				// System.out.println(in.nextLine());
				String temp = in.nextLine();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.print(e);
		}

		finally {
			// in.close();
		}
	}

	public ArrayList<Color> getColors(int size) {
		ArrayList<Color> result = new ArrayList<>(10);

		if (size >= colors.size()) {
			throw new IndexOutOfBoundsException("Make sure input is less than "
					+ colors.size());
		}
		while (result.size() < size) {
			int pos = new Random().nextInt(256);
			result.add(colors.get(pos));
		}

		return result;

	}

	public ArrayList<Color> getDistinct() {

		ArrayList<Color> result = new ArrayList<>();

		int pos = 0;
		while (colors.size() > pos) {
			if (!containColor(result, colors.get(pos).name))
				result.add(colors.get(pos));
			pos++;
		}

		return result;

	}

	public ArrayList<Color> getDistinctRandom(int pSize) {
		ArrayList<Color> result = new ArrayList<>(10);
		ArrayList<Color> tempCols = getDistinct();
		int size = -2;
		if (pSize == -1)
			size = tempCols.size() - 1;
		else if (pSize >= 0)
			size = pSize;
		if (size > tempCols.size()) {
			throw new IndexOutOfBoundsException("Make sure input is less than "
					+ tempCols.size());
		}

		int pos = -1;
		while (result.size() < size) {
			if (!containColor(result, tempCols.get(pos = new Random()
					.nextInt(tempCols.size() - 1)).name))
				result.add(tempCols.get(pos));
		}

		return result;

	}

	// public ArrayList<Color> getSystemDefault() {
	//
	// ArrayList<Color> result = new ArrayList<>(5);
	// return result;
	//
	// }

	public boolean containColor(ArrayList<Color> pColors, String pName) {
		boolean result = false;
		if (pColors.size() != 0) {
			int pos = 0;
			while (pos < pColors.size()
					&& !pColors.get(pos).name.regionMatches(0, pName, 0, 3)) {
				pos++;
			}
			if (pos < pColors.size())
				result = true;
		}
		return result;
	}

	public ArrayList<Color> getBasic(boolean onlyBasic) {
		ArrayList<Color> result = new ArrayList<>();
		String[] tempBasic;
		if (onlyBasic)
			tempBasic = new String[] { "red", "orange", "yellow", "green",
					"blue", "brown", "indigo", "black", "violet", "hot",
					"pink", "purple", "gold", "white", "cream", "cyan" };
		else {
			tempBasic = new String[] { "red", "orange", "yellow", "green",
					"blue", "brown", "indigo", "black", "violet", "hot",
					"pink", "purple", "gold", "white", "grey", "cream", "cyan" };
		}
		ArrayList<String> basicColors = new ArrayList<String>();
		for (String tempStr : tempBasic) {
			basicColors.add(tempStr);
		}
		// int basicPos=0;

		for (Color tmpCol : colors) {
			int listPos = 0;
			while (listPos < basicColors.size()
					&& !tmpCol.name
							.regionMatches(true, 0,
									(String) basicColors.get(listPos), 0,
									basicColors.get(listPos).length())) {
				listPos++;
			}
			if (listPos < basicColors.size()) {
				result.add(tmpCol);
				System.out.println(tmpCol.name);
				// basicColors.remove(listPos);
			}

		}
		return result;
	}

	class Color {
		String name = "";
		String colorValue = null;

		public Color(String name, String colorValue) {
			this.name = name;
			this.colorValue = colorValue;
		}
	}

	public static void main(String[] arg0) {

		ColorUtil col = new ColorUtil();
		for (Color color : col.getColors(10)) {

			System.out.println(String.format("%-20s%-8s", color.name,
					color.colorValue));
		}
	}
}
