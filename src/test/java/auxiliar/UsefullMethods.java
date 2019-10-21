package auxiliar;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Locale;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.ibm.icu.text.RuleBasedNumberFormat;

import pages.HomePage;

public class UsefullMethods {

    private int parceNumberFromString(String number) {
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(Locale.UK, RuleBasedNumberFormat.SPELLOUT);
        int result = -1;
        try {
            result = formatter.parse(number).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void waitSeconds(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Object invokeMethod(String methodName) {
        try {
            HomePage hp = new HomePage();
            return hp.getClass().getDeclaredMethod(methodName).invoke(hp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SelenideElement getElementByName(String number, String element) {
        ElementsCollection collection = getElementsByName(element);
        if (number.equals("")) {
            return collection.get(0);
        } else {
            int index = parceNumberFromString(number);
            if (index > -1) {
                return collection.get(index - 1);
            }
        }
        throw new NullPointerException();
    }

    public SelenideElement getElementByName(String element) {
        return getElementByName("", element);
    }

    private ElementsCollection getElementsByName(String element) {
        Selenide.switchTo().defaultContent();
        return (ElementsCollection) invokeMethod(getMethodName(element, "get"));
    }

    private String getMethodName(String element, String prefix) {
        return prefix + element.replaceAll(" ", "");
    }
}
