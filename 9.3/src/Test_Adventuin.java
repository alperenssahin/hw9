

public class Test_Adventuin {

	public static void main(String[] args) {
		Adventuin a = new Adventuin ("AdvenTux", 123, new RgbColor(3, 2, 7, 0), HatType.FISHY_HAT,
				Language.ENGLISH);
		System.out.println(a.toString());
	}

}
