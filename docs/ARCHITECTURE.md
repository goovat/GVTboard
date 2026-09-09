# GVTboard Architecture

## Core Design Principles

GVTboard is designed as an independently implemented Android keyboard
platform with separated domain, storage, UI, and integration layers.

Each meaningful domain concept, service, workflow, and test should live
in its own file where practical.

The application must avoid unnecessary artificial limitations and must
remain memory-safe when handling large user content.

---

## Clipboard Architecture

### Large Content Requirement

GVTboard must support very large clipboard content without imposing an
arbitrary small application-level size limit.

This applies to both:

- Large text content
- Files and other URI-based clipboard content

The practical limits are determined by Android, the underlying storage,
the filesystem, available device storage, and the permissions provided by
the source content provider.

GVTboard itself must not introduce an arbitrary small size restriction.

### Large Text

Large text must be persisted using storage appropriate for potentially
large content.

The architecture must avoid unnecessarily duplicating large text in
memory.

Large text should be loaded or processed on demand where practical.

The clipboard subsystem must not reject text merely because it exceeds
a small fixed character limit.

### Files

Files must be represented primarily through URI/content references rather
than loading the entire file into application memory.

Clipboard file metadata should be capable of representing:

- URI/reference
- MIME type
- Display name
- Size when available
- Creation/capture timestamp
- Pinned state
- Content type

When Android URI permissions are required, GVTboard must preserve the
appropriate access required for the clipboard item's lifetime, subject
to Android platform restrictions.

### Memory Safety

Clipboard operations must be designed so that large content does not
need to be fully loaded into RAM.

The implementation should prefer:

- Streaming
- URI-based access
- On-demand reads
- Persistent storage
- Metadata-first operations

Avoid:

- Unnecessary full-file byte-array copies
- Unbounded in-memory clipboard caches
- Duplicate copies of large pinned content

### Clipboard History Capacity

The planned clipboard history capacity is:

- 100 pinned items
- 50 unpinned items
- 150 items total

This is an item-count policy, not a content-size policy.

Pinned items are protected from normal unpinned-history eviction.

A large item must not be rejected solely because it is large.

If storage pressure becomes relevant, the implementation should use
explicit storage-management policies rather than silently imposing an
arbitrary small per-item size limit.

### Pinning

Pinning an item must not unnecessarily duplicate its underlying content.

A pinned clipboard item should reference the same persisted content or
URI representation where possible.

### Persistence

Clipboard history should use persistent storage rather than relying
solely on process memory.

The architecture should allow clipboard items to survive:

- Keyboard process restarts
- Application process restarts
- Device/application lifecycle events where Android permits persistence

### Security and Privacy

Clipboard data can contain sensitive information.

The clipboard architecture must therefore consider:

- Secure persistence
- Android clipboard access restrictions
- URI permission lifetime
- Avoiding unnecessary logging of clipboard content
- Avoiding accidental exposure of clipboard data
- Safe deletion of expired/evicted content

### Planned Clipboard Components

The clipboard subsystem should remain separated into independently
testable components, including where appropriate:

- ClipboardItem
- ClipboardRepository
- ClipboardManager
- ClipboardPolicy
- RecentClipboardStore
- PinnedClipboardStore
- LargeContentStore
- File/URI content handler
- Clipboard eviction policy
- Clipboard security/privacy policy

The exact classes may evolve during implementation, but large-content
handling must remain an explicit architectural responsibility.

---

## Keyboard Architecture

The keyboard domain is being developed incrementally:

1. KeyboardState
2. KeyAction
3. KeyEventHandler
4. KeyboardController
5. KeyboardLayout
6. SymbolLayout
7. Keyboard UI
8. InputConnection integration
9. Editing behavior
10. Suggestions/autocomplete/autocorrect
11. Clipboard subsystem
12. Settings, themes, languages, voice, and other keyboard features

Clipboard implementation must therefore wait until the keyboard
foundation is sufficiently stable.

---

## Testing Requirements

Large-content clipboard behavior must have dedicated tests.

Tests should cover, where applicable:

- Large text persistence
- Large text retrieval
- Large text without artificial size rejection
- File URI preservation
- MIME type preservation
- File metadata preservation
- Pinned large content
- Unpinned large content
- 100 pinned-item capacity
- 50 unpinned-item capacity
- Eviction of unpinned items
- Protection of pinned items
- Persistence across repository recreation
- Memory-safe handling
- Content deletion/cleanup
- Invalid or inaccessible URIs
- Storage failures

Tests must be added incrementally with each clipboard component.

---

## Size-Limit Policy

GVTboard must distinguish between:

1. Platform/storage limitations
2. Provider/URI limitations
3. Available device storage
4. GVTboard-imposed limitations

GVTboard should not introduce category 4 as an arbitrary small
per-item clipboard limit.

The goal is:

> Support clipboard content up to the practical limits of the Android
> device, storage system, and source content provider while remaining
> persistent, reliable, and memory-safe.
