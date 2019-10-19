import java.util.Scanner; //инициализация сканера для считывания

public class Main //класс Main
{
    private static int minIncome = 200000; // минимальная сумма дохода
    private static int maxIncome = 900000; //максимальная сумма дохода

    private static int officeRentCharge = 140000; //Аренда офиса
    private static int telephonyCharge = 12000; //Оплата телефона
    private static int internetAccessCharge = 7200; //оплата интернета

    private static int assistantSalary = 45000; //Зарплата асистента
    private static int financeManagerSalary = 90000; //Зарплата бугалтера

    private static double mainTaxPercent = 0.24; //Процент налога
    private static double managerPercent = 0.15; //Процент менеджера

    private static double minInvestmentsAmount = 100000; //Минимальный порог инвестирования

    public static void main(String[] args)
    {
        while(true) //цикл
        {
            System.out.println("Введите сумму доходов компании за месяц " + "(от 200 до 900 тысяч рублей): "); //вывод запроса на ввод
            int income = (new Scanner(System.in)).nextInt(); //Получаем значение с командной строки

            if(!checkIncomeRange(income)) { //условие выполняется если сумма доходов компании за месяц в пределах заданного диапазона
                continue;
            }

            double managerSalary = income * managerPercent; //зарплата менеджера сумма доходов*Процент менеджера
            double pureIncome = income - managerSalary - calculateFixedCharges(); //чистый доход = сумма доходов - зарплата менеджера - постоянные доходы
            double taxAmount = mainTaxPercent * pureIncome; // расчёт суммы налога = Процент налога * чистый доход
            double pureIncomeAfterTax = pureIncome - taxAmount; //прибыль = чистый доход - сумму налога

            boolean canMakeInvestments = pureIncomeAfterTax >= minInvestmentsAmount; // присвоение true если прибыль больше либо равна минимальному порогу инвестирования

            System.out.println("Зарплата менеджера: " + managerSalary); //отображение зарплаты менеджера
            System.out.println("Общая сумма налогов: " + (taxAmount > 0 ? taxAmount : 0)); //вывод суммы налогов если есть чистый доход
            System.out.println("Компания может инвестировать: " + (canMakeInvestments ? "да" : "нет")); //выводит может ли команда инвестировать на основании строки 37
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!"); //выводится если чистая прибль меньше 0
            }
        }
    }

    private static boolean checkIncomeRange(int income) //проверка суммы доходов компании за месяц
    {
        if(income < minIncome) //проверка нижнего значения
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome) //проверка верхнего значения
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges() //подсчёт постоянных расходов
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
