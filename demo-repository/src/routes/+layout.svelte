<script>
	import '../app.css';
	import Header from "./header.svelte";

	const {children} = $props();
</script>


<div class="w-full">
	<Header/>
	{@render children()}
</div>