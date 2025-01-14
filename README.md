# Практические задания  

1. **В компьютерной игре герой (класс Hero)** может перемещаться между двумя точками (метод move) различными способами: идти пешком, ехать на лошади, лететь и т. п. Реализовать классы, позволяющие пользователю выбирать и менять в ходе выполнения программы способ перемещения героя, используя паттерн “стратегия” (strategy). Продемонстрировать работу реализованных классов.  

2. **Написать аннотацию с целочисленным параметром**. Создать класс, содержащий публичные, защищенные и приватные методы (2–3 каждого вида) с параметрами, аннотировать любые из них. Вызвать из другого класса все аннотированные защищенные и приватные методы столько раз, сколько указано в параметре аннотации. Вызовающий методы код не должен зависеть от количества и типов параметров этих методов.  

3. **Реализовать программу-переводчик**.  
   3.1. При запуске программы выполняется чтение словаря из файла, записанного в следующем формате:  
   `слово или выражение | перевод`  
   
   3.2. Затем запрашивается пользовательский ввод в консоли и выполняется перевод введённого текста.  
   
   3.3. Перевод осуществляется по следующим правилам:  
   - регистр букв игнорируется  
   - если искомого слова нет в словаре – выводится без перевода  
   - если есть несколько подходящих вариантов, выбирается вариант с максимальной длиной левой части. Например:  
     - словарь:  
       - `look | смотреть`  
       - `look forward | ожидать`  
     - текст:  
       - `dog look to the window, dog look forward`  
     - перевод:  
       - `dog смотреть to the window, dog ожидать`  
   
   3.4. Результат перевода выводится в консоль.  
   
   3.5. Создать и применить пользовательские исключения:  
   - `InvalidFileFormatException` см. пункт 3.1.  
   - `FileReadException` файла не существует, нет доступа к файлу и т.д.  

4. **С использованием только Stream API реализовать следующие методы**:  
   - метод, возвращающий среднее значение списка целых чисел;  
   - метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»;  
   - метод, возвращающий список квадратов всех встречающихся только один раз элементов списка;  
   - метод, принимающий на вход коллекцию и возвращающий ее последний элемент или кидающий исключение, если коллекция пуста;  
   - метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел или 0, если чётных чисел нет;  
   - метод, преобразовывающий все строки в списке в Map, где первый символ – ключ, оставшиеся – значение.
