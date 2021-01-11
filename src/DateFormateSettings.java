import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormateSettings {
	String dateFormateCorrection(String str) {

		// String str="Jan 27, 2021";
		Pattern pp = Pattern.compile("\\d+");

		Matcher m = pp.matcher(str);
		String[] strar = new String[5];
		int i = 0;
		while (m.find()) {

			strar[i++] = m.group();
		}
//		System.out.println(strar[0] + "and" + strar[1]);
		String st = str.substring(0, 3);
		String finalFromat = strar[0] + "-" + st + "-" + strar[1];
//		System.out.println("Month:" + st);
//		System.out.println("final format:" + strar[0] + "-" + st + "-" + strar[1]);

		return finalFromat;

	}

}
