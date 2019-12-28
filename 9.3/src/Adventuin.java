
public class Adventuin {

	private String name;
	private int height;
	private RgbColor color;
	private HatType hat;
	private Language language;

	public Adventuin(String name, int size, RgbColor color, HatType hat,
			Language language) {
		if (name != null && size > 0 && color != null && hat != null
				&& language != null) {
			this.name = name;
			this.height = size;
			this.color = new RgbColor(color.getBitDepth(), color.getRed(),
					color.getGreen(), color.getBlue());
			this.hat = hat;
			this.language = language;
		} else
			System.out.println("please provide attributes correctly!!!");
	}

	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public RgbColor getColor() {
		return color;
	}

	public HatType getHatType() {
		return hat;
	}

	public Language getLanguage() {
		return language;
	}

	public String getLocalizedChristmasGreeting(String greeterName) {
		if (greeterName != null && greeterName != "") {
			String message = "";
			switch (language) {
			case ENGLISH:
				message = greeterName + " wishes you a Merry Christmas!";
				break;
			case GERMAN:
				message = "Fr�hliche Weihnachten w�nscht dir " + greeterName
						+ "!";
				break;
			default:
				break;
			}
			return message;
		} else
			return new String("please provide greeter name correctly!!!");
	}

	@Override
	public String toString() {
		if (getName() != null && getHeight() != -1 && getColor() != null
				&& getHatType() != null && getLanguage() != null)
			return new String("Name: " + getName() + " Size: " + getHeight()
					+ " Color: " + getColor().toString() + " Hat Type: "
					+ getHatType() + " Language: " + getLanguage());
		else
			return new String("Some of the field(s) is/are null !!!");
	}
}