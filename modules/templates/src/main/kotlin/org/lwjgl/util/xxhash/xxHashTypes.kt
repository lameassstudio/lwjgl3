/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.util.xxhash

import org.lwjgl.generator.*

val XXHASH_PACKAGE = "org.lwjgl.util.xxhash"

fun config() {
	packageInfo(
		XXHASH_PACKAGE,
		"""
		Contains bindings to <a href="https://github.com/Cyan4973/xxHash">xxHash</a>, an extremely fash non-cryptographic hash algorithm.

		xxHash successfully completes the <a href="https://code.google.com/p/smhasher/wiki/SMHasher">SMHasher</a> test suite which evaluates collision,
		dispersion and randomness qualities of hash functions.
		"""
	)

	Generator.registerLibraryInit(XXHASH_PACKAGE, "LibXXHash", "xxhash", setupAllocator = true)
}

val unsigned_long_long = IntegerType("long long", PrimitiveMapping.LONG, unsigned = true)

val XXH32_hash_t = typedef(unsigned_int, "XXH32_hash_t")
val XXH64_hash_t = typedef(unsigned_long_long, "XXH32_hash_t")

val XXH32_state_t = struct(XXHASH_PACKAGE, "XXH32State", nativeName = "XXH32_state_t") {
	documentation = "32-bit XXH state for stack allocation. Unstable API."

	unsigned_long_long.member("total_len", "")
	unsigned.member("seed", "")
	unsigned.member("v1", "")
	unsigned.member("v2", "")
	unsigned.member("v3", "")
	unsigned.member("v4", "")
	unsigned.array("mem32", "", size = 4)
	unsigned.member("memsize", "")
}.nativeType
val XXH32_state_t_p = XXH32_state_t.p

val XXH64_state_t = struct(XXHASH_PACKAGE, "XXH64State", nativeName = "XXH64_state_t") {
	documentation = "64-bit XXH state for stack allocation. Unstable API."

	unsigned_long_long.member("total_len", "")
	unsigned_long_long.member("seed", "")
	unsigned_long_long.member("v1", "")
	unsigned_long_long.member("v2", "")
	unsigned_long_long.member("v3", "")
	unsigned_long_long.member("v4", "")
	unsigned_long_long.array("mem64", "", size = 4)
	unsigned.member("memsize", "")
}.nativeType
val XXH64_state_t_p = XXH64_state_t.p

val XXH_errorcode = "XXH_errorcode".enumType

val XXH32_canonical_t_p = struct_p(XXHASH_PACKAGE, "XXH32Canonical", nativeName = "XXH32_canonical_t", mutable = false) {
	documentation = "32-bit canonical representation."

	unsigned_char.array("digest", "the digest in canonical representation", size = 4)
}

val XXH64_canonical_t_p = struct_p(XXHASH_PACKAGE, "XXH64Canonical", nativeName = "XXH64_canonical_t", mutable = false) {
	documentation = "64-bit canonical representation."

	unsigned_char.array("digest", "the digest in canonical representation", size = 8)
}