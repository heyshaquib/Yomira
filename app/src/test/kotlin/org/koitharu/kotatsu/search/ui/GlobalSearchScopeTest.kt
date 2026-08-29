package org.koitharu.kotatsu.search.ui

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class GlobalSearchScopeTest {

	@Test
	fun `scope toggle is highlighted inside the empty search hint`() {
		val menu = resource("menu/opt_search_suggestion.xml")
		val activity = source("main/ui/MainActivity.kt")

		assertTrue(!menu.contains("""android:id="@+id/action_search_scope""""))
		assertTrue(activity.contains("searchView.hint=SpannableString(hintText)"))
		assertTrue(activity.contains(").uppercase()"))
		assertTrue(activity.contains("editText.hintas?Spanned"))
		assertTrue(activity.contains("searchSuggestionViewModel.toggleSearchScope()"))
		assertTrue(activity.contains("Layout.getDesiredWidth(hint,0,scopeStart,textPaint)"))
		assertTrue(activity.contains("event.x!inscopeStartX..scopeEndX"))
	}

	@Test
	fun `global search uses a transparent selection handle window`() {
		val layouts = listOf(
			"layout/activity_main.xml",
			"layout-w600dp-land/activity_main.xml",
			"layout-w840dp/activity_main.xml",
		)

		assertTrue(layouts.all { resource(it).contains("@style/ThemeOverlay.Kotatsu.SearchText") })
		assertTrue(resource("values/styles.xml").contains("""android:popupBackground">@android:color/transparent"""))
		assertTrue(resource("values/styles.xml").contains("""android:popupElevation">0dp"""))
	}

	@Test
	fun `scope choice is persisted and refreshes suggestions`() {
		val settings = source("core/prefs/AppSettings.kt")
		val suggestions = source("search/ui/suggestion/SearchSuggestionViewModel.kt")

		assertTrue(settings.contains("varisGlobalSearchNovelScope:Boolean"))
		assertTrue(settings.contains("KEY_GLOBAL_SEARCH_NOVEL_SCOPE"))
		assertTrue(suggestions.contains("observeAsFlow(AppSettings.KEY_GLOBAL_SEARCH_NOVEL_SCOPE)"))
		assertTrue(suggestions.contains("settings.isGlobalSearchNovelScope=!settings.isGlobalSearchNovelScope"))
	}

	@Test
	fun `global results use the Explore source classification everywhere`() {
		val search = source("search/ui/multi/SearchViewModel.kt")

		assertTrue(search.contains(".filter{it.isNovelSource==isNovelScope}"))
		assertTrue(search.contains(".filter{it.source.isNovelSource==isNovelScope}"))
		assertTrue(search.contains("searchLocal():SearchResultsListModel?=if(isNovelScope){null}"))
	}

	@Test
	fun `search filters default off and persist later user changes`() {
		val settings = source("core/prefs/AppSettings.kt")
		val search = source("search/ui/multi/SearchViewModel.kt")
		val menu = source("search/ui/multi/SearchMenuProvider.kt")

		assertTrue(settings.contains("varisSearchPinnedOnly:Boolean"))
		assertTrue(settings.contains("varisSearchLocalOnly:Boolean"))
		assertTrue(settings.contains("prefs.getBoolean(KEY_SEARCH_PINNED_ONLY,false)"))
		assertTrue(settings.contains("prefs.getBoolean(KEY_SEARCH_LOCAL_ONLY,false)"))
		assertTrue(settings.contains("putBoolean(KEY_SEARCH_PINNED_ONLY,value)"))
		assertTrue(settings.contains("putBoolean(KEY_SEARCH_LOCAL_ONLY,value)"))
		assertTrue(search.contains("MutableStateFlow(settings.isSearchPinnedOnly)"))
		assertTrue(search.contains("MutableStateFlow(settings.isSearchLocalOnly)"))
		assertTrue(menu.contains("action_filter_pinned_only)?.run{isChecked=viewModel.isPinnedOnly"))
		assertTrue(menu.contains("action_filter_local_only)?.isChecked=viewModel.isLocalOnly"))
	}

	private fun resource(relativePath: String): String {
		return sequenceOf(
			File("src/main/res", relativePath),
			File("app/src/main/res", relativePath),
		).firstOrNull(File::isFile)?.readText()
			?: error("Cannot find production resource: $relativePath")
	}

	private fun source(relativePath: String): String {
		return sequenceOf(
			File("src/main/kotlin/org/koitharu/kotatsu", relativePath),
			File("app/src/main/kotlin/org/koitharu/kotatsu", relativePath),
		).firstOrNull(File::isFile)?.readText()
			?.replace(Regex("""//[^\r\n]*"""), "")
			?.replace(Regex("""\s+"""), "")
			?: error("Cannot find production source: $relativePath")
	}
}
