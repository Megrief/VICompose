# VICompose
Переписанный на Compose проект VeryInterestingTest: <https://github.com/Megrief/VeryInterestingTest>. Посмотреть процесс разработки можно в исходном проекте.

Небольшое тестовое приложение. Для поиска картинок с использованием api <https://serper.dev/>

Стек:
 - Kotlin
 - JetpackCompose
 - ComposeNavigation
 - Retrofit2
 - OkHttp3
 - Coroutines
 - Room (для ветки с кешированием результатов)
 - Paging3
 - Shared Elements Transitions

Ввиду некорректной работы кеширования с RemoteMediator, выделил его в отдельную ветку [with_caching](https://github.com/Megrief/VICompose/tree/with_caching). В основной оставил пагинацию без кеширования.
