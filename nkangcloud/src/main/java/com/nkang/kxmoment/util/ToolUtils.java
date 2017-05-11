package com.nkang.kxmoment.util;

public class ToolUtils {
	public static double getnolevelcalc(double salary) {
		double level1, level2, level3, level4, level5, level6, tax = 0;
		level1 = 1455 * 0.03;
		level2 = level1 + (4155 - 1455) * 0.1;
		level3 = level2 + (7755 - 4155) * 0.2;
		level4 = level3 + (27255 - 7755) * 0.25;
		level5 = level4 + (41255 - 27255) * 0.30;
		level6 = level5 + (57505 - 41255) * 0.35;

		if (salary <= 1455)
			tax = salary * 0.03;
		if (salary > 1455 && salary <= 4155)
			tax = level1 + (salary - 1455) * 0.1;
		if (salary > 4155 && salary <= 7755)
			tax = level2 + (salary - 4155) * 0.2;
		if (salary > 7755 && salary <= 27255)
			tax = level3 + (salary - 7755) * 0.25;
		if (salary > 27255 && salary <= 41255)
			tax = level4 + (salary - 27255) * 0.3;
		if (salary > 41255 && salary <= 57505)
			tax = level5 + (salary - 41255) * 0.35;
		if (salary > 57505)
			tax = level6 + (salary - 57505) * 0.45;
		return tax;
	}
	public static double getlevelcalc(double salary) {
		double level1, level2, level3, level4, level5, level6, tax = 0;
		level1 = 1500 * 0.03;
		level2 = level1 + (4500 - 1500) * 0.1;
		level3 = level2 + (9000 - 4500) * 0.2;
		level4 = level3 + (35000 - 9000) * 0.25;
		level5 = level4 + (55000 - 35000) * 0.30;
		level6 = level5 + (80000 - 35000) * 0.35;

		if (salary <= 1500)
			tax = salary * 0.03;
		if (salary > 1500 && salary <= 4500)
			tax = level1 + (salary - 1500) * 0.1;
		if (salary > 4500 && salary <= 9000)
			tax = level2 + (salary - 4500) * 0.2;
		if (salary > 9000 && salary <= 35000)
			tax = level3 + (salary - 9000) * 0.25;
		if (salary > 35000 && salary <= 55000)
			tax = level4 + (salary - 35000) * 0.3;
		if (salary > 55000 && salary <= 80000)
			tax = level5 + (salary - 55000) * 0.35;
		if (salary > 80000)
			tax = level6 + (salary - 80000) * 0.45;
		return tax;
	}

}
