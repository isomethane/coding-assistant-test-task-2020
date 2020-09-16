# coding-assistant-test-task-2020

Это решение тестовой задачи для проекта "AST Transformations for the Coding Assistant project"

## Описание задачи

В строке необходимо расставить открывающие и закрывающие скобки так, чтобы до середины строки шли открывающие, а после — закрываюшие. Например, для строки abcde -> a(b(c)d)e. При этом необходимо поддерживать несколько режимов, которые может комбинировать пользователь:

- Расставлять скобки по краям строки или нет:
    - (a(b(c)d)e) или a(b(c)d)e
- Вставлять пустые скобки в середину строки или нет в случае четной длины:
    - a(b()c)d или a(bc)d
- Расставлять круглые, фигурные или квадратные скобки, а также чередовать любые их комбинации:
    - a(b(c)d)e, a{b{c}d}e или a[b[c]d]e;
    - a(b[c(d[e]f)g]h)i, a(b{c(d{e}f)g}h)i, a{b[c{d[e]f}g]h}i или a(b{c[d(e)f]g}h)i

Пример: в режиме "расставлять по краям; вставлять в середину; чередовать ( и [" для строки abcdefghi выдать (a[b(c[d()e]f)g]h)

## Аргументы

```
usage: [-h] STRING [-e] [-c] --brackets BRACKETS

required arguments:
  --brackets BRACKETS   opening brackets sequence


optional arguments:
  -h, --help            show this help message and exit

  -e, --edge            insert edge brackets

  -c, --center          insert center brackets (for strings with even length)


positional arguments:
  STRING                string to transform
```

## Запуск

```
gradle run --args='ARGS'
```

Например:
```
gradle run --args='"abcdef" --brackets=[( -c'
a[b(c[]d)e]f
```