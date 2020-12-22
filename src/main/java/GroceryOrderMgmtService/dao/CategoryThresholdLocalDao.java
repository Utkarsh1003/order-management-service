package GroceryOrderMgmtService.dao;

import GroceryOrderMgmtService.Constants;
import GroceryOrderMgmtService.config.CategoryThresholdConfig;
import GroceryOrderMgmtService.enums.ItemCategory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryThresholdLocalDao implements CategoryThresholdDao{
    private static CategoryThresholdLocalDao instance;
    public static CategoryThresholdLocalDao getInstance(){
        if(instance == null)
            instance = new CategoryThresholdLocalDao();

        return instance;
    }

    private Map<Date, Map<ItemCategory, Integer>> dayWiseItemCategoryThreshold;
    private CategoryThresholdLocalDao(){
        this.dayWiseItemCategoryThreshold = new HashMap<>();
    }

    @Override
    public Integer getLimit(Date date, ItemCategory itemCategory) {
        if(dayWiseItemCategoryThreshold.isEmpty())
            return 0;

        if(dayWiseItemCategoryThreshold.get(date) == null || dayWiseItemCategoryThreshold.get(date).isEmpty())
            return 0;

        return dayWiseItemCategoryThreshold.get(date).get(itemCategory);
    }

    public void populateData(List<CategoryThresholdConfig> categoryThresholdConfig){
        categoryThresholdConfig.forEach(itemCategoryThreshold -> {
            String stringDate = itemCategoryThreshold.getDate();
            try {
                Date date = new SimpleDateFormat(Constants.dateParserPattern).parse(stringDate);
                dayWiseItemCategoryThreshold.put(date, itemCategoryThreshold.getItemCategoryThresholdMap());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
