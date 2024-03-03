package CWException;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DataInput {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные в формате: Фамилия Имя Отчество Дата_рождения Номер_телефона пол");
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                System.out.println("Вы ввели больше или меньше данных, чем требуется.");
                continue;
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
                format.setLenient(false);
                format.parse(birthDate);

                Long.parseLong(phoneNumber);

                if (!gender.equals("f") && !gender.equals("m")) {
                    throw new IllegalArgumentException("Укажите пол -  'f' (женский) или 'm' (мужской).");
                }
            } catch (ParseException e) {
                System.out.println("Установленный формат даты рождения - dd.mm.yyyy.");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Номер телефона должен быть целым числом.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                FileWriter writer = new FileWriter(surname + ".txt", true);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(surname).append(" ")
                        .append(name).append(" ")
                        .append(patronymic).append(" ")
                        .append(birthDate).append(" ")
                        .append(phoneNumber).append(" ")
                        .append(gender).append("\n");
                writer.write(stringBuilder.toString());
                writer.close();
                System.out.println("Пользователь успешно зарегистрирован.Ы");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

