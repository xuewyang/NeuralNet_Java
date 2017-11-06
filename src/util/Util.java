package util;

public class Util {
	public static double[] ConcatArr(double[] arr1, double[] arr2) {
		double[] res = new double[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++)
			res[i] = arr1[i];
		for (int i = arr1.length; i < arr1.length + arr2.length; i++)
			res[i] = arr2[i-arr1.length];
		return res;
	}
	
	public static double[] Delta(double[] targetValue, double[] realValue) {
		double[] res = new double[targetValue.length];
		for (int i = 0; i < targetValue.length; i++)
			res[i] = targetValue[i] - realValue[i];
		return res;
	}
	
	public static double[] ShortenArr(double[] arr, int newLength) {
		double[] res = new double[newLength];
		for (int i = 0; i < newLength; i++)
			res[i] = arr[i];
		return res;
	}
	
	public static double[] Copy(double[] arr) {
		double[] res = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			res[i] = arr[i];
		return res;
	}
	
	
	public static void main(String[] args) {
		double[] arr1 = {1, 2, 3};
		double[] arr2 = {3, 4, 2, 5};
		double[] arr3 = {-1, 3, 9};
		double[] res, res2, res3, res4;
		res = Util.ConcatArr(arr1, arr2);
		res2 = Util.Delta(arr1, arr3);
		res3 = Util.ShortenArr(arr2, 3);
		res4 = Util.Copy(arr1);
		for (int i = 0; i < res.length; i++)
			System.out.println(res[i]);
		System.out.println();
		for (int i = 0; i < res2.length; i++)
			System.out.println(res2[i]);
		System.out.println();
		for (int i = 0; i < res3.length; i++)
			System.out.println(res3[i]);
		System.out.println();
		for (int i = 0; i < res4.length; i++)
			System.out.println(res4[i]);
	}
}
