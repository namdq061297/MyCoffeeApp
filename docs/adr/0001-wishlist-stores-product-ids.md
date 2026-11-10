# Wishlist stores Product ids, not Product objects

The Wishlist persists only an ordered list of Product ids (newest first) and resolves each id
against `MockData` when displayed, unlike the Cart, which persists whole `Product` objects as Gson
JSON. Storing whole objects freezes data that changes underneath it: `Product.imageUrl` is an
`R.drawable` id that shifts between builds, and Gson writes `null` into non-null Kotlin fields when
a stored model gains a field. Ids avoid both, at the cost of needing a Product lookup on every read;
an id with no matching Product is skipped.
