import functions.*;
import functions.basic.*;
import functions.meta.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // создаем тестовые функции
        FunctionPoint[] points1 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 5.0)
        };

        FunctionPoint[] points2 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 3.0),
                new FunctionPoint(2.0, 5.0)
        };

        FunctionPoint[] points3 = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 4.0), // отличается от points1
                new FunctionPoint(2.0, 5.0)
        };

        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points2);
        ArrayTabulatedFunction arrayFunc3 = new ArrayTabulatedFunction(points3);

        LinkedListTabulatedFunction listFunc1 = new LinkedListTabulatedFunction(points1);
        LinkedListTabulatedFunction listFunc2 = new LinkedListTabulatedFunction(points2);
        LinkedListTabulatedFunction listFunc3 = new LinkedListTabulatedFunction(points3);

        // тестирование toString()
        System.out.println("\n1. Тестирование toString():");
        System.out.println("ArrayFunc1: " + arrayFunc1.toString());
        System.out.println("ListFunc1: " + listFunc1.toString());

        // тестирование equals()
        System.out.println("\n2. Тестирование equals():");
        System.out.println("arrayFunc1.equals(arrayFunc2): " + arrayFunc1.equals(arrayFunc2));
        System.out.println("arrayFunc1.equals(arrayFunc3): " + arrayFunc1.equals(arrayFunc3));
        System.out.println("listFunc1.equals(listFunc2): " + listFunc1.equals(listFunc2));
        System.out.println("listFunc1.equals(listFunc3): " + listFunc1.equals(listFunc3));

        // сравнение между разными классами
        System.out.println("arrayFunc1.equals(listFunc1): " + arrayFunc1.equals(listFunc1));
        System.out.println("listFunc1.equals(arrayFunc1): " + listFunc1.equals(arrayFunc1));

        // тестирование hashCode()
        System.out.println("\n3. Тестирование hashCode():");
        System.out.println("arrayFunc1.hashCode(): " + arrayFunc1.hashCode());
        System.out.println("arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
        System.out.println("arrayFunc3.hashCode(): " + arrayFunc3.hashCode());
        System.out.println("listFunc1.hashCode(): " + listFunc1.hashCode());
        System.out.println("listFunc2.hashCode(): " + listFunc2.hashCode());
        System.out.println("listFunc3.hashCode(): " + listFunc3.hashCode());

        // проверка согласованности equals() и hashCode()
        System.out.println("\n4. Проверка согласованности equals() и hashCode():");
        System.out.println("arrayFunc1.equals(arrayFunc2) && arrayFunc1.hashCode() == arrayFunc2.hashCode(): " +
                (arrayFunc1.equals(arrayFunc2) && (arrayFunc1.hashCode() == arrayFunc2.hashCode())));
        System.out.println("listFunc1.equals(listFunc2) && listFunc1.hashCode() == listFunc2.hashCode(): " +
                (listFunc1.equals(listFunc2) && (listFunc1.hashCode() == listFunc2.hashCode())));

        // изменение объекта и проверка изменения хэш-кода
        System.out.println("\n5. Изменение объекта и проверка хэш-кода:");
        ArrayTabulatedFunction arrayFunc4 = new ArrayTabulatedFunction(points1);
        System.out.println("Исходный хэш-код: " + arrayFunc4.hashCode());

        // изменяем одну координату на несколько тысячных
        arrayFunc4.setPointY(1, 2.001);
        System.out.println("Хэш-код после изменения Y[1] на 2.001: " + arrayFunc4.hashCode());
        System.out.println("arrayFunc1.equals(arrayFunc4) после изменения: " + arrayFunc1.equals(arrayFunc4));

        // тестирование clone()
        System.out.println("\n6. Тестирование clone():");

        // клонирование ArrayTabulatedFunction
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc1.clone();
        System.out.println("arrayFunc1.equals(arrayClone): " + arrayFunc1.equals(arrayClone));
        System.out.println("arrayFunc1 == arrayClone: " + (arrayFunc1 == arrayClone));

        // клонирование LinkedListTabulatedFunction
        LinkedListTabulatedFunction listClone = (LinkedListTabulatedFunction) listFunc1.clone();
        System.out.println("listFunc1.equals(listClone): " + listFunc1.equals(listClone));
        System.out.println("listFunc1 == listClone: " + (listFunc1 == listClone));

        // проверка глубокого клонирования
        System.out.println("\n7. Проверка глубокого клонирования:");

        // изменяем исходный объект
        System.out.println("До изменения - arrayFunc1[1]: " + arrayFunc1.getPoint(1));
        arrayFunc1.setPointY(1, 10.0); // изменяем исходный объект
        System.out.println("После изменения arrayFunc1[1]: " + arrayFunc1.getPoint(1));
        System.out.println("Клон arrayClone[1]: " + arrayClone.getPoint(1));
        System.out.println("Клон не изменился: " + arrayClone.getPoint(1).equals(new FunctionPoint(1.0, 3.0)));

        // проверка для LinkedList
        System.out.println("До изменения - listFunc1[1]: " + listFunc1.getPoint(1));
        listFunc1.setPointY(1, 15.0); // изменяем исходный объект
        System.out.println("После изменения listFunc1[1]: " + listFunc1.getPoint(1));
        System.out.println("Клон listClone[1]: " + listClone.getPoint(1));
        System.out.println("Клон не изменился: " + listClone.getPoint(1).equals(new FunctionPoint(1.0, 3.0)));

        // дополнительная проверка с FunctionPoint
        System.out.println("\n8. Тестирование FunctionPoint методов:");
        FunctionPoint pt1 = new FunctionPoint(1.5, 2.5);
        FunctionPoint pt2 = new FunctionPoint(1.5, 2.5);
        FunctionPoint pt3 = new FunctionPoint(1.5, 2.6);

        System.out.println("pt1: " + pt1.toString());
        System.out.println("pt1.equals(pt2): " + pt1.equals(pt2));
        System.out.println("pt1.equals(pt3): " + pt1.equals(pt3));
        System.out.println("pt1.hashCode(): " + pt1.hashCode());
        System.out.println("pt2.hashCode(): " + pt2.hashCode());
        System.out.println("pt3.hashCode(): " + pt3.hashCode());

        FunctionPoint ptClone = (FunctionPoint) pt1.clone();
        System.out.println("pt1.equals(ptClone): " + pt1.equals(ptClone));
        System.out.println("pt1 == ptClone: " + (pt1 == ptClone));
    }
}