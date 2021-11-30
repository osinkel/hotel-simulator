package utils;

import java.util.HashMap;
import java.util.Map;
import exception.LocalizationException;

public class LocalizationUtils {
    private static final Map<Integer, String> RU_MESSAGES = initRuMessages();

    private static Map<Integer, String> initRuMessages() {
        Map<Integer, String> messages = new HashMap<Integer, String>();
        messages.put(1, "Номер добавлен");
        messages.put(2, "Ошибка! Номер не добавлен");
        messages.put(3, "Услуга добавлена");
        messages.put(4, "Ошибка! Услуга не добавлена");
        messages.put(5, "Ошибка! Такого номера не существует");
        messages.put(6, "Гость заселен в номер");
        messages.put(7, "Ошибка! Гость не заселен в номер, т.к. данный номер ремонтируемый или уже занят");
        messages.put(8, "Ошибка! Гость не заселен в номер, т.к. должна быть введена текущая дата или будущая, в формате даты: dd/mm/yyyy");
        messages.put(9, "Ошибка! Гость не может быть выселен из номера, т.к. номер был пуст");
        messages.put(10, "Цена за номер изменена");
        messages.put(11, "Ошибка! Цена за номер не изменена, т.к. такого номера не существует");
        messages.put(12, "Цена за услугу изменена");
        messages.put(13, "Ошибка! Цена за услугу не изменена, т.к. такой услуги не существует");
        messages.put(14, "Сортировка номеров по цене");
        messages.put(15, "Сортировка номеров по вместимости");
        messages.put(16, "Сортировка номеров по звездам");
        messages.put(17, "Сортировка гостей по имени");
        messages.put(18, "Сортировка гостей по дате выселения");
        messages.put(19, "Всего свободных номеров: ");
        messages.put(20, "Всего постояльцев: ");
        messages.put(21, "Ошибка! Должна быть введена текущая дата или будущая, формат даты: dd/mm/yyyy");
        messages.put(22, "В номере никто не проживает");
        messages.put(23, "Гость должен заплатить ");
        messages.put(24, "Услуга заказана в номер");
        messages.put(25, "Ошибка! Такой услуги не существует");
        messages.put(26, "Ошибка! Такого гостя не существет");
        messages.put(27, "Номер на ремонте");
        messages.put(28, "Номер свободен");
        messages.put(29, "Ошибка! В номере проживает гость");
        messages.put(30, "Услуги гостя отсортированы по цене");
        messages.put(31, "Услуги гостя отсортированы по дате заказа");
        messages.put(32, "Список услуг заказанных гостем");
        messages.put(33, "Список номеров, которые будут свободны с ");
        messages.put(34, "№ Вместимость Цена   Звезды");
        messages.put(35, "Имя\t\tДата выселения");
        messages.put(36, "Гость выселен в номер");
        messages.put(37, "Статус номера изменен");
        messages.put(38, "Список номеров, которые будут свободны с ");
        messages.put(39, "Номера и услуги отсортированы по разделу");
        messages.put(40, "Номера и услуги отсортированы по цене");
        messages.put(41, "Информация о номере");
        messages.put(42, "Данная сущность успешно экспортирована");
        messages.put(43, "Ошибка! Данная сущность не была экспортирована");
        return messages;
    }

    public static String localize(int code){
        String msg;
        msg = RU_MESSAGES.get(code);
        if (msg == null) {
            throw new LocalizationException("Message not found");
        }
        return msg;
    }
}
