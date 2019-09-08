/*
 * Designed and developed by 2019 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.whatif

/**
 * WhatIf is kotlin extensions for expressing a single if-else statement, nullable and boolean.
 */

/** An expression for invoking [whatIf] when the [given] boolean is true. */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: (T) -> Boolean?,
  crossinline whatIf: () -> Unit
) {

  if (given(this) == true) {
    whatIf()
  }
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: (T) -> Boolean?,
  crossinline whatIf: () -> Unit,
  crossinline whatIfNot: () -> Unit
) {

  if (given(this) == true) {
    whatIf()
  } else {
    whatIfNot()
  }
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: Boolean?,
  crossinline whatIf: T.() -> Unit
): T {

  if (given == true) {
    this.apply { whatIf() }
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: Boolean?,
  crossinline whatIf: T.() -> Unit,
  crossinline whatIfNot: T.() -> Unit
): T {

  if (given == true) {
    this.apply { whatIf() }
  } else {
    this.apply { whatIfNot() }
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] lambda's return value is true.
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: () -> Boolean?,
  crossinline whatIfDo: T.() -> Unit
): T {

  if (given() == true) {
    this.apply { whatIfDo() }
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] lambda's return value is true.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
  given: () -> Boolean?,
  crossinline whatIfDo: T.() -> Unit,
  crossinline whatIfNot: T.() -> Unit
): T {

  if (given() == true) {
    this.apply { whatIfDo() }
  } else {
    this.apply { whatIfNot() }
  }
  return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean value is false, the result value is the [default].
 * It is useful when the receiver [T] and the result [R] should be different.
 */
@WhatIfInlineOnly
inline fun <T, R> T.whatIfLet(
  given: Boolean?,
  default: R,
  crossinline whatIf: (T) -> R
): R {

  if (given == true) {
    return whatIf(this)
  }
  return default
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 */
@WhatIfInlineOnly
inline fun <T, R> T.whatIfLet(
  given: Boolean?,
  crossinline whatIf: (T) -> R,
  crossinline whatIfNot: (T) -> R
): R {

  if (given == true) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/** An expression for invoking [whatIf] when the [T] target object is not null. */
@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
  crossinline whatIf: (T) -> Unit
) {

  if (this != null) {
    whatIf(this)
  }
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * If the [T] target is null, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
  crossinline whatIf: (T) -> Unit,
  crossinline whatIfNot: (T?) -> Unit
) {

  if (this != null) {
    whatIf(this)
  } else {
    whatIfNot(this)
  }
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * If the [T] target is null, [whatIfNot] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 */
@WhatIfInlineOnly
inline fun <T, R> T?.whatIfNotNullWith(
  crossinline whatIf: (T) -> R,
  crossinline whatIfNot: (T?) -> R
): R {

  if (this != null) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/** An expression for invoking [whatIf] when the target object is not null and true. */
@WhatIfInlineOnly
inline fun Boolean?.whatIf(
  crossinline whatIf: () -> Unit
) {

  if (this == true) {
    whatIf()
  }
}

/**
 * An expression for invoking [whatIf] when the target object is not null and true.
 * If the target is null or false, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun Boolean?.whatIf(
  crossinline whatIf: () -> Unit,
  crossinline whatIfNot: () -> Unit
) {

  if (this == true) {
    whatIf()
  } else {
    whatIfNot()
  }
}