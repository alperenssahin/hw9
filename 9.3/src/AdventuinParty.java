
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class AdventuinParty {

	public static Map<HatType, List<Adventuin>> groupByHatType(List<Adventuin> adventuins) {
		if (adventuins != null) {
			Map<HatType, List<Adventuin>> myMap = new HashMap<HatType, List<Adventuin>>();
			List<Adventuin> listSCHat = new ArrayList<>();
			List<Adventuin> listRHat = new ArrayList<>();
			List<Adventuin> listFHat = new ArrayList<>();
			List<Adventuin> listNoHat = new ArrayList<>();

			for (Adventuin adventuin : adventuins) {
				switch (adventuin.getHatType()) {
				case SANTA_CLAUS:
					listSCHat.add(adventuin);
					break;
				case REINDEER:
					listRHat.add(adventuin);
					break;
				case FISHY_HAT:
					listFHat.add(adventuin);
					break;
				case NO_HAT:
					listNoHat.add(adventuin);
				default:
					break;
				}
			}

			myMap.put(HatType.SANTA_CLAUS, listSCHat);
			myMap.put(HatType.REINDEER, listRHat);
			myMap.put(HatType.FISHY_HAT, listFHat);
			myMap.put(HatType.NO_HAT, listNoHat);

			return myMap;
		} else
			return null;
	}

	public static void printLocalizedChristmasGreetings(List<Adventuin> adventuins) {

		if (adventuins != null) {
			int maxSize = 0;
			for (Adventuin adventuin : adventuins) {
				if (adventuin.getHeight() > maxSize)
					maxSize = adventuin.getHeight();
			}

			// en kucuk size li olanlardan eklemeye basliyor
			List<Adventuin> sortedAdventuins = new ArrayList<>();
			for (int i = 0; i <= maxSize; i++) {
				for (Adventuin adventuin : adventuins) {
					if (adventuin.getHeight() == i)
						sortedAdventuins.add(adventuin);
				}
			}

			for (Adventuin adventuin : sortedAdventuins) {
				System.out.println(adventuin
						.getLocalizedChristmasGreeting(adventuin.getName()));
			}
		}
	}

	public static Map<HatType, List<Adventuin>> getAdventuinsWithLongestNamesByHatType(List<Adventuin> adventuins) {

		if (adventuins != null) {
			Map<HatType, List<Adventuin>> groupedByHat = groupByHatType(adventuins);

			int maxSCHat = 0;
			int maxRHat = 0;
			int maxFHat = 0;
			int maxNoHat = 0;

			for (Adventuin adventuin : adventuins) {
				switch (adventuin.getHatType()) {
				case SANTA_CLAUS:
					if(adventuin.getName().length() > maxSCHat)
						maxSCHat = adventuin.getName().length();
					break;
				case REINDEER:
					if(adventuin.getName().length() > maxRHat)
						maxRHat = adventuin.getName().length();
					break;
				case FISHY_HAT:
					if(adventuin.getName().length() > maxFHat)
						maxFHat = adventuin.getName().length();
					break;
				case NO_HAT:
					if(adventuin.getName().length() > maxNoHat)
						maxNoHat = adventuin.getName().length();
					break;
				default:
					break;
				}
			}

			List<Adventuin> listSCHat = new ArrayList<>();
			List<Adventuin> listRHat = new ArrayList<>();
			List<Adventuin> listFHat = new ArrayList<>();
			List<Adventuin> listNoHat = new ArrayList<>();

			for (Adventuin adventuin : adventuins) {
				switch (adventuin.getHatType()) {
				case SANTA_CLAUS:
					if(adventuin.getName().length() == maxSCHat)
						listSCHat.add(adventuin);
					break;
				case REINDEER:
					if(adventuin.getName().length() == maxRHat)
						listRHat.add(adventuin);
					break;
				case FISHY_HAT:
					if(adventuin.getName().length() == maxFHat)
						listFHat.add(adventuin);
					break;
				case NO_HAT:
					if(adventuin.getName().length() == maxNoHat)
						listNoHat.add(adventuin);
					break;
				default:
					break;
				}
			}

			Map<HatType, List<Adventuin>> myMap = new HashMap<HatType, List<Adventuin>>();
			myMap.put(HatType.SANTA_CLAUS, listSCHat);
			myMap.put(HatType.REINDEER, listRHat);
			myMap.put(HatType.FISHY_HAT, listFHat);
			myMap.put(HatType.NO_HAT, listNoHat);

			return myMap;
		} else
			return null;
	}

	// Stream lerle ilgili: map'lerdeki sikinti ayni key degerine birden fazla kayit acamamamiz. 2. kayit ilkini eziyor map yapisinda.
	public static Map<Integer, Double> getAverageColorBrightnessByHeight(
			List<Adventuin> adventuins) {

		Map<Integer,List<Adventuin>> roundedSizeAdventuin = new HashMap<>();

		for (Adventuin adventuin : adventuins) {
			try{
				List<Adventuin> la = roundedSizeAdventuin.get(roundValueToNear10(adventuin.getHeight()));
				la.add(adventuin);
				roundedSizeAdventuin.put(roundValueToNear10(adventuin.getHeight()), la);
			}catch (Exception ex){
				List<Adventuin> la = new ArrayList<>();
				la.add(adventuin);
				roundedSizeAdventuin.put(roundValueToNear10(adventuin.getHeight()), la);
			}
		}

		Map<Integer, Double> sizeAvgColor = new HashMap<>();

		roundedSizeAdventuin.forEach((integer, adventuinList) -> {
			double sumavg = 0;
			for(int i =0; i<adventuinList.size();i++){
				RgbColor8Bit tmp = adventuinList.get(i).getColor().toRgbColor8Bit();
				sumavg += (0.2126 * tmp.getRed() + 0.7152 * tmp.getGreen() + 0.0722 * tmp.getBlue()) / 255;
			}
			double avg = sumavg/adventuinList.size();
			sizeAvgColor.put(integer,avg);
		});
		//Map -> Stream -> Filter -> Double gibi birsey olmali sanki
		
		return sizeAvgColor;
	}
	public static Map<HatType, Double> getDiffOfAvgHeightDiffsToPredecessorByHatType(List<Adventuin> adventuinList){
		Map<HatType,Double> res = new HashMap<>();
		Map<HatType,List<Adventuin>> fMap = groupByHatType(adventuinList);
		fMap.forEach((hatType, adventuinList1) -> {
			if(adventuinList1.size()>1){
				int sumNegativeDif = 0;
				int sumPositiveDif = 0;
				int negativeCount = 0;
				int positiveCount = 0;
				for(int i =0; i<adventuinList1.size()-1;i++){
					int diff = 0;
					if(i == 0){
						diff = adventuinList1.get(i).getHeight() - adventuinList1.get(adventuinList1.size()-1).getHeight();
					}else{
						diff = adventuinList1.get(i).getHeight() - adventuinList1.get(i-1).getHeight();
					}
					if(diff > 0){
						sumPositiveDif += diff;
						negativeCount++;
					}else if(diff < 0){
						sumNegativeDif += diff;
						positiveCount++;
					}
				}
				double absoluteDifferance = (double)sumPositiveDif/positiveCount - (double)sumNegativeDif/negativeCount;
				res.put(hatType,absoluteDifferance);

			}else{
				res.put(hatType,0.0);
			}

		});
		return res;
	}


	private static int roundValueToNear10(int value) {
		if (value > 0) {
			int firstOption = (value / 10) * 10;
			int secondOption = firstOption + 10;

			if (value - firstOption > secondOption - value)
				return secondOption;
			else
				return firstOption;
		} else
			return -1;
	}
}
