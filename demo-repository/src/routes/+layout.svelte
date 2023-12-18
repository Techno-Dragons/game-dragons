<script>
	import '../app.css';

	const {children} = $props();
</script>


<div class="w-full">
	{@render children()}
</div>