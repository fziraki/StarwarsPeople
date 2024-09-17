# StarwarsPeople
Show characters of Star Wars with search feature

<p dir="rtl">
از معماری mvvm و clean استفاده کردم همین طور features رو جدا کردم که اگه در آینده پروژه مالتی ماژول شد راحت باشه
di از hilt استفاده کردم

از api سرچ استفاده کردم روی people و سرچ سمت کلاینت رو آپتیمایز نوشتم با debounce یک ثانیه که به ازای هر حرف ریکوئست نزنه
(تو اسکیل بزرگ اگر api با دیتای کمی داشته باشیم که تقریبا استاتیکه و آپدیت نمیشه زود به زود ، کش کنیم روی دیتابیس یا هدر نتورک و سرچ لوکال انجام بدیم بهتره)

روی api سرچ ، کرکتری که برمیگردوند id نداشت و کلا چند تا api اینطوری بود که عوضش url داشتن که id داخلش بود و می تونستی مستقیم با Url@ رتروفیت Get کنی.

برای نمایش ارور از sharedflow استفاده کردم که با rotate دوباره نمایش نده

فلو ها رو با collectAsStateWithLifecycle که lifecycle aware باشه

برای آپتیمایز بودن لیست نتایج کرکترها موقع سرچ از url به عنوان کلید استفاده کردم

صفحه character details هم که چندتا api call هست کوروتین های جدا با viewmodelscope هستن که با هم تداخلی ندارن و 
دیتای هر کدوم رسید تو stateFlow خودش ذخیره و نمایش داده میشه

لایبرری thirdparty هم استفاده نکردم همه native

</p>





