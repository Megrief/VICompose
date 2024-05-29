# VICompose
Небольшое тестовое приложение для поиска картинок с использованием api <https://serper.dev/>


Переписанный на Compose проект VeryInterestingTest: <https://github.com/Megrief/VeryInterestingTest>. Посмотреть процесс разработки можно в исходном проекте.


Стек:
 - Kotlin
 - JetpackCompose
 - ComposeNavigation
 - Retrofit2
 - OkHttp3
 - Coroutines
 - MVVM
 - Room (для ветки с кешированием результатов)
 - Paging3
 - Shared Elements Transitions


Нюансы требующие упоминания:
 - Ввиду некорректной работы кеширования с RemoteMediator, выделил его в отдельную ветку [with_caching](https://github.com/Megrief/VICompose/tree/with_caching). В основной оставил пагинацию без кеширования.
 - Shared Elements привязан не к элементам списков, а к контейнерам, т.к. SharedTransitionLayout не видит картинку в ленивом списке. Ошибка отображения: **updateAcquireFence: Did not find frame compose**
 - При долгом пролистывании элементов в Pager неточно передается позиция элемента обратно в Grid.
